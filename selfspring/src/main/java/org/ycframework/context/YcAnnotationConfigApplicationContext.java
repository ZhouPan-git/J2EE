package org.ycframework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.annotation.*;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zp
 * @Date 2023/7/27 16:00
 * @PackageName:org.ycframework.context
 * @ClassName: YcAnnotationConfigApplicationContext
 * @Description:
 * @Version 1.0
 */
public class YcAnnotationConfigApplicationContext implements YcApplicationContext{
    private Logger logger= LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);
    //存每个带托管的bean的定义信息
    private Map<String, YcBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //存每个实例化的bean
    private Map<String,Object> beanMap=new ConcurrentHashMap<String,Object>();
    //存系统属性  db.properties
    private Properties pros;

    public YcAnnotationConfigApplicationContext (Class... configClasses){
        try {
        //读取系统的属性
        pros=System.getProperties();
        List<String> toScanPackagePath=new ArrayList<String>();
        for (Class cls:configClasses){
            if (cls.isAnnotationPresent(YcComponentScan.class)==false){
                continue;
            }
            String[] basePackages=null;
            //扫描配置类上的  @YcComponentScan注解  读取扫描的包
            if (cls.isAnnotationPresent(YcComponentScan.class)){
                //如果有则说明 此配置类上有@YcComponentScan 则读取basePackages
                YcComponentScan ycComponentScan= (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                basePackages= ycComponentScan.basePackages();
                if (basePackages==null||basePackages.length<=0){
                    basePackages=new String[1];
                    basePackages[0]=cls.getPackage().getName();
                }
                logger.info(cls.getName()+"类上有@YcComponentScan注解，它要扫描的路径为："+basePackages[0]);
            }
            //开始扫描这些basePackages包下的bean,并加载包装成BeanDefinition对象， 存到beanDefinitionMap中
            recursiveLoadBeanDefinition(basePackages);
        }
        //循环 beanDefinitionMap ，创建bean（是否懒加载，是否单例），存到beanMap
            createBean();
        //循环使用托管的beanMap中的bean看属性和方法上是否有@Autowired @Resource @Value  考虑DI
            doDi();
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //开始扫描这些basePackages包下的bean,并加载包装成BeanDefinition对象， 存到beanDefinitionMap中
    private void recursiveLoadBeanDefinition(String[] basePackages) {
        for (String basePackage : basePackages){
            //将包名中的 .替换成 路径中 的 /
            String packagePath=basePackage.replaceAll("\\.","/");
            // target/classes  /com/yc
            // Enumeration集合 URL:每个资源的路径
            Enumeration<URL> files=null;
            try{
                files=Thread.currentThread().getContextClassLoader().getResources(packagePath);
                //循环这个files  看是否是我要加载的资源
                while(files.hasMoreElements()){
                    URL url=files.nextElement();
                    logger.trace("当前正在递归加载："+url.getFile());
                    //查找此包下的类        com/yc 全路径   com/yc包名
                    findPackageClasses(url.getFile(),basePackage);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void findPackageClasses(String packagePath, String basePackage)  {
        //路径异常的处理  如果前面有 / 则去掉它
        if (packagePath.startsWith("/")){
            packagePath=packagePath.substring(1);
        }
        //取这个路径下所有的字节码文件（因为目录下有可能用其他的目录）
        File file=new File(packagePath);
        //只取后缀名为 .class 的字节码
        //写法一：
//        File[] classFiles=file.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                if (pathname.getName().endsWith(".class")||pathname.isDirectory()){
//                    return true;
//                }
//                return false;
//            }
//        });
        //写法二： lamda写法
        File[] classFiles=file.listFiles( (pathname)->{
            if (pathname.getName().endsWith(".class")||pathname.isDirectory()){
                return true;
            }
            return false;
        });
        if (classFiles.length<=0||classFiles==null){
            return;
        }
        //循环此classFiles
        for (File cf : classFiles){
            if (cf.isDirectory()){
                //继续递归
                logger.trace("递归："+cf.getAbsolutePath()+",它对应的包名为："+(basePackage+"."+cf.getName()));
                findPackageClasses(cf.getAbsolutePath(),basePackage+"."+cf.getName());
            }else {
                //是class文件 则取出文件 判断此文件对应的class中是否有 @Component 注解
                URLClassLoader uc=new URLClassLoader(new URL[]{});
                //                                    userDaoImpl.class
                Class cls=null;
                try{
                    cls=uc.loadClass(basePackage+"."+cf.getName().replaceAll(".class",""));
                    //TODO:可以支持YcComponent子注解
                    if (cls.isAnnotationPresent(YcComponent.class)
                        ||(cls.isAnnotationPresent(YcService.class)
                        ||cls.isAnnotationPresent(YcRepository.class)
                        ||cls.isAnnotationPresent(YcConfiguration.class)
                        ||cls.isAnnotationPresent(YcController.class))){
                        logger.info("加载到一个待托管的类："+cls.getName());
                        //TODO:包装成BeanDefinition
                        YcBeanDefinition bd=new YcBeanDefinition();
                        if (cls.isAnnotationPresent(YcLazy.class)){
                            bd.setLazy(true);
                        }
                        if (cls.isAnnotationPresent(YcScope.class)){
                            YcScope ycScope= (YcScope) cls.getAnnotation(YcScope.class);
                            String scope=ycScope.value();
                            bd.setScope(scope);
                        }
                        //  classInfo中保存这个待托管的类的包路径   com.yc.dao.UserDaoImpl
                        //  cls.newInstance()
                        bd.setClassInfo(basePackage+"."+cf.getName().replaceAll(".class",""));
                        //存到  beanDefinitionMap 中 “beanid”-》“BeanDefinition对象”
                        String beanId=getBeanId(cls);
                        this.beanDefinitionMap.put(beanId,bd);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //获取一个待托管类的beanId   1：@Component  直接用类名（首字母小写）  2：@Component("beanId")
    //另外  YcConfiguration 注解则用类的全路径名作beanId
    private String getBeanId(Class cls) {
        YcComponent ycComponent= (YcComponent) cls.getAnnotation(YcComponent.class);
        YcController ycController= (YcController) cls.getAnnotation(YcController.class);
        YcService ycService= (YcService) cls.getAnnotation(YcService.class);
        YcRepository ycRepository= (YcRepository) cls.getAnnotation(YcRepository.class);
        YcConfiguration ycConfiguration= (YcConfiguration) cls.getAnnotation(YcConfiguration.class);
        if (ycConfiguration!=null){
            return cls.getName();  //全路径名
        }
        String beanId=null;
        if (ycComponent!=null){
            beanId=ycComponent.value();
        }else if (ycController!=null){
            beanId=ycController.value();
        }else if (ycRepository!=null){
            beanId=ycRepository.value();
        }else if (ycService!=null){
            beanId=ycService.value();
        }
        if (beanId==null||"".equalsIgnoreCase(beanId)){
            String typeName=cls.getSimpleName();
            beanId=typeName.substring(0,1).toLowerCase()+typeName.substring(1);
        }
        return beanId;
    }

    private void createBean() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (Map.Entry<String,YcBeanDefinition> entry : beanDefinitionMap.entrySet()){
            String beanId=entry.getKey();
            YcBeanDefinition ybd=entry.getValue();
            if (!ybd.isLazy()&&!ybd.getScope().equalsIgnoreCase("prototype")){
                String classInfo=ybd.getClassInfo();
                Object obj=Class.forName(classInfo).newInstance();
                beanMap.put(beanId,obj);
                logger.trace("spring容器托管了："+beanId+"=>"+classInfo);
            }
        }
    }

    private void doDi() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //循环的是 beanMap 这是托管bean
        for (Map.Entry<String,Object> entry : beanMap.entrySet()){
            String beanId=entry.getKey();
            Object beanObj=entry.getValue();
            //情况一：属性上有 @YcResource 注解的情况
            Field[] fields=beanObj.getClass().getDeclaredFields();
            for (Field field : fields){
                if (field.isAnnotationPresent(YcResource.class)){
                    YcResource ycResource=field.getAnnotation(YcResource.class);
                    String toDiBeanId=ycResource.name();
                    //从beanMap中 注入 是否singleton 是否lazy
                    Object obj= getToDiBean(toDiBeanId);
                    //注入
                    field.setAccessible(true); //因为属性是private的 所以要将它 accessible设为true
                    field.set(beanObj,obj);//
                }
            }
            //情况二：方法上有 @YcResource 注解的情况
            //情况三：构造方法上有@YcResource 注解的情况
        }
    }

    //从beanMap中 注入 是否singleton 是否lazy
    private Object getToDiBean(String toDiBeanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (beanMap.containsKey(toDiBeanId)){
            return beanMap.get(toDiBeanId);
        }
        //判断beanMap中 没有此bean是否因为lazy
        if (!beanDefinitionMap.containsKey(toDiBeanId)){
            throw new RuntimeException("spring容器中没有加载此class："+toDiBeanId);
        }
        YcBeanDefinition bd=beanDefinitionMap.get(toDiBeanId);
        if (bd.isLazy()){
            //是因为懒，所以没有托管
            String classPath=bd.getClassInfo();
            Object beanObj=Class.forName(classPath).newInstance();
            beanMap.put(toDiBeanId, beanObj);
            return beanObj;
        }
        //是否因为prototype
        if (bd.getScope().equalsIgnoreCase("prototype")){
            //是因为原型，所以没有托管
            String classPath=bd.getClassInfo();
            Object beanObj=Class.forName(classPath).newInstance();
            //beanMap.put(toDiBeanId, beanObj);原型模式下，每次getBean创建一次bean 所以beanMap不存
            return beanObj;
        }
        return null;
    }

    @Override
    public Object getBean(String beanid) {
        YcBeanDefinition bd=this.beanDefinitionMap.get(beanid);
        if (bd == null) {
            throw new RuntimeException("容器中没有加此bean");
        }
        String scope=bd.getScope();
        if ("prototype".equalsIgnoreCase(scope)){
            //原型模式，每次getBean创建
            Object obj=null;
            try {
                obj=Class.forName(bd.getClassInfo()).newInstance();
                //这种原型模式创建的bean不能存到beanMap中
                return obj;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (this.beanMap.containsKey(beanid)){
            return this.beanMap.get(beanid);
        }
        if (bd.isLazy()){
            Object obj=null;
            try {
                obj=Class.forName(bd.getClassInfo()).newInstance();
                //懒加载的bean是要保存的
                this.beanMap.put(beanid, obj);
                return obj;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return obj;
        }
        return null;
    }
}