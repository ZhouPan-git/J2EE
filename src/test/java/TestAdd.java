import com.yc.mavenTest.Add;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author zp
 * @Date 2023/7/21 20:50
 * @PackageName:PACKAGE_NAME
 * @ClassName: TestAdd
 * @Description:
 * @Version 1.0
 */
public class TestAdd {
    private Add a;

    @Before
    public void setUp() throws Exception {
        a = new Add();
    }

    @Test
    public void testAdd(){
        Add a = new Add();
        int a1=1;
        int b=2;
        Assert.assertEquals(a.add(1,2),9);
    }
    @Test
    public void testAdd1(){
        Add a = new Add();
        int a1=1;
        int b=2;
        Assert.assertEquals(a.add(1,2),3);
    }
    @Test
    public void testCon() throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","123456");
        Assert.assertNotNull(con);
    }
}