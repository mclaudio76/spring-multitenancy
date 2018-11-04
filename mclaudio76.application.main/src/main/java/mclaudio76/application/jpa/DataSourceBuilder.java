package mclaudio76.application.jpa;

import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import com.mysql.cj.jdbc.MysqlXADataSource;


public class DataSourceBuilder {

	public DataSource createMySqlDatasource(String XAResourceID, String url, String user, String pwd) {
		try {
			MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
			mysqlXaDataSource.setUrl(url);
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
			mysqlXaDataSource.setPassword(pwd);
			mysqlXaDataSource.setUser(user);
			mysqlXaDataSource.setServerTimezone("Europe/Rome");
			return mysqlXaDataSource;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public Properties getHibernateProperties(String dialect) {
		Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.autocommit", "false");
        //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.dialect", dialect);
        // MOST IMPORTANT ! We need to tell Hibernate to use JTA as transaction coordinator.
        properties.setProperty("hibernate.transaction.coordinator_class", "jta");
        properties.setProperty("hibernate.transaction.factory_class","org.hibernate.transaction.JTATransactionFactory");
        properties.setProperty("hibernate.transaction.jta.platform", ApplicationJtaPlatform.class.getCanonicalName());
        return properties;
	}
	
	
	public DataSource wrapXADataSource(XADataSource ds, String uniqueXAName) {
		AtomikosDataSourceBean wrapper = new AtomikosDataSourceBean();
		wrapper.setUniqueResourceName(uniqueXAName);
		wrapper.setMaxPoolSize(100);
		wrapper.setMinPoolSize(1);
		wrapper.setXaDataSource(ds); 
		return wrapper; 
	}  
	
}
