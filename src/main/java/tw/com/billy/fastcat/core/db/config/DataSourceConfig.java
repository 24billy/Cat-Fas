package tw.com.billy.fastcat.core.db.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
/**
 *
 * @author Billy Shih
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="tw.com.billy.fastcat.core")
public class DataSourceConfig {
	
	@Autowired
	private Environment environment;

	@Bean(name="dataSource")
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/CatFas");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
         
        return dataSource;
	}
	
}
