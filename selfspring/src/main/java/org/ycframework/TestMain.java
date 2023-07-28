package org.ycframework;

import com.yc.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author zp
 * @Date 2023/7/27 16:03
 * @PackageName:org.ycframework
 * @ClassName: TestMain
 * @Description:
 * @Version 1.0
 */
public class TestMain {
    public static void main(String[] args) {
        Logger logger= LoggerFactory.getLogger(App.class);
        logger.error("error");
        logger.warn("warning");
        logger.info("info");
        logger.debug("debug");
    }
}