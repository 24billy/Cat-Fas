package tw.com.billy.fastcat;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tw.com.billy.fastcat.core.db.config.DataSourceConfig;


/**
 * 
 * @author Billy
 */
@RunWith(SpringJUnit4ClassRunner.class)
//指定Spring使用的dataSource and Dao
@ContextConfiguration(classes = DataSourceConfig.class)
//@Transactional
@WebAppConfiguration    
public class SpringTest extends AbstractTest {

}
