package com.yc.springtest1.system;

/**
 * @Author zp
 * @Date 2023/7/26 9:04
 * @PackageName:com.yc.springtest1
 * @ClassName: Container
 * @Description:
 * @Version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *  1.能存大量对象（大学生bmi | 。。。）=》集合
 *  2。测量的接口 =》具体怎么测，用户决定
 *  3. 筛选的接口=》具体怎么筛选 ，用户决定
 *  4.返回最大值，最小值对象。。。
 */
//TODO:要用spring完成托管
@Component
public class Container<T> {
    private List<T> objs=new ArrayList<T>();

    //TODO:要用spring完成DI
    //@Autowired
    //@Qualifier(value = "")
    @Resource(name = "bmiMeasure")
    private Measure measure;
    @Resource(name = "bmiFilter")
    private ContainerFilter filter;

    private T max;
    private T min;
    private double avg;
    private double sum;

    //添加对象的方法
    public void add(T t){
        //TODO:
        //判断t是否 合格  调用筛选的实现
        if (filter!=null) {
            if (filter.doFilter(t) == false) {
                return;
            }
        }
        //添加到 objs
        objs.add(t);
        //判断大小 记录max，min，计算avg  =》调用 measure的实现
        if (objs.size()==1){
            max=t;
            min=t;
        }else {
            //测出值判断大小
            double val=this.measure.doMeasure(t);
            double maxval=this.measure.doMeasure(max);
            double minval=this.measure.doMeasure(min);
            if (val>maxval){
                max=t;
            }if (val<minval){
                min=t;
            }
        }
            sum+= measure.doMeasure(t);
            avg=sum/objs.size();
        }
    public T getMax(){
        return max;
    }
    public T getMin(){
        return min;
    }
    public double getAvg() {
        return avg;
    }

    //有效的测量对象有多少个
    public int size(){
        return objs.size();
    }

    //系统复位
    public void clearAll(){
        objs=new ArrayList<T>();
        measure=null;
        filter=null;
        max=null;
        min=null;
        avg=0;
    }
}
