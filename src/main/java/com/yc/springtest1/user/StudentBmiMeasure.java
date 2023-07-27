package com.yc.springtest1.user;

import com.yc.springtest1.system.Measure;
import org.springframework.stereotype.Component;

/**
 * @Author zp
 * @Date 2023/7/26 9:39
 * @PackageName:com.yc.springtest1.user
 * @ClassName: StudentBmiMeasure
 * @Description: 学生bmi指数测量接口
 * @Version 1.0
 */
@Component("bmiMeasure")
public class StudentBmiMeasure implements Measure {
    //算法
    @Override
    public double doMeasure(Object obj) {
        if (obj==null){
            throw new RuntimeException("待测数据异常");
        }
        if (!(obj instanceof Student)){
            throw new RuntimeException("待测数据异常");
        }
        Student s=  (Student)obj;
        return s.getWeight()/(s.getHeight()*s.getHeight());
    }

}