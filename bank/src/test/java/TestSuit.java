import com.yc.AccountDaoJdbcTemplateImplTest;
import com.yc.OpRecordDaoJdbcTemplateImplTest;
import com.yc.Test1;
import com.yc.Test2_DataSourceConfig;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Author zp
 * @Date 2023/8/2 15:36
 * @PackageName:PACKAGE_NAME
 * @ClassName: TestSuit
 * @Description:
 * @Version 1.0
 */
@RunWith(Suite.class)
@ Suite.SuiteClasses({Test1.class, Test2_DataSourceConfig.class, AccountDaoJdbcTemplateImplTest.class,AccountDaoJdbcTemplateImplTest.class, OpRecordDaoJdbcTemplateImplTest.class})
public class TestSuit {
}