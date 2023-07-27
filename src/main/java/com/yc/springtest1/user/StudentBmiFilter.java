package com.yc.springtest1.user;

import com.yc.springtest1.system.ContainerFilter;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @Author zp
 * @Date 2023/7/26 9:39
 * @PackageName:com.yc.springtest1.user
 * @ClassName: StudentBmiFilter
 * @Description:
 * @Version 1.0
 */
@Component("bmiFilter")
public class StudentBmiFilter implements ContainerFilter {
    Logger logger=Logger.getLogger(String.valueOf(StudentBmiFilter.class));
    @Override
    public boolean doFilter(Object obj) {
        if (obj==null){
            return false;
        }if (!(obj instanceof Student)){
            return false;
        }
        Student s= (Student) obj;
        if (s.getName()==null||"".equals(s.getName())){
            return false;
        }
        if (s.getWeight()<30||s.getWeight()>100){
            logger.warning("体重数据异常"+obj.toString());
            return false;
        }
        if (s.getHeight()<1||s.getHeight()>2.7){
            logger.warning("身高数据异常"+obj.toString());
            return false;
        }
        return true;
    }
}