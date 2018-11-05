package mclaudio76.application.jpa;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataBaseConfig {

	@Bean
	public DataSourceBuilder dataSourceBuilder() {
		return new DataSourceBuilder();
	}
	
	@Bean
	public ApplicationJtaPlatform setupJtaPlatform(TransactionManager txManager, UserTransaction userTx) {
		return new ApplicationJtaPlatform(txManager, userTx);
	}
	
	
	
	@Bean(name="STD")
    public EntityManagerFactory tenant1DataSource(DataSourceBuilder dsBuilder, ApplicationJtaPlatform platform) {
        return createEntityManagerFactory(dsBuilder, platform, "STD", "jdbc:mysql://localhost:3306/persistencejpa", "spring", "spring");
    }
	
	@Bean(name="ACME")
    public EntityManagerFactory tenant2DataSource(DataSourceBuilder dsBuilder, ApplicationJtaPlatform platform) {
        return createEntityManagerFactory(dsBuilder, platform, "ACME", "jdbc:mysql://localhost:3306/secondpersistence", "spring", "spring");
    }
	
	
	@Bean
	@Primary
    public EntityManagerFactory selectCurrentTenantEntityManager(List<EntityManagerFactory> lst) {
		return new ProxyEntityManagerFactory(lst);
    }
	
	
	/*@Bean
	@Primary
	public EntityManagerFactory selectEntityManagerFactory(List<EntityManagerFactory> factories) {
		System.out.println(" TenantContext tx "+TenantContext.getTenantID());
		for(EntityManagerFactory item : factories) {
			String tenant = (String) item.getProperties().getOrDefault("x-tenant-id", "");
			if(tenant.trim().equals(TenantContext.getTenantID())) {
				return item;
			}
		}
		return factories.get(0);
	}*/
	
	
	
	private EntityManagerFactory createEntityManagerFactory(DataSourceBuilder dsBuilder, ApplicationJtaPlatform platform, String tenantID, String URL, String user, String pwd) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        DataSource dataSource = dsBuilder.createMySqlDatasource(tenantID, URL, user, pwd);
        em.setDataSource(dsBuilder.wrapXADataSource((XADataSource)dataSource, tenantID));
        em.setPackagesToScan(new String[] { "mclaudio76.application.core.entities" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName(tenantID);
        em.setJpaProperties(dsBuilder.getHibernateProperties("org.hibernate.dialect.MySQL5Dialect"));
        em.afterPropertiesSet();
        EntityManagerFactory emf = em.getNativeEntityManagerFactory();
        emf.getProperties().putIfAbsent("x-tenant-id", tenantID);
        return emf;
    }
	
	
	
	
	
}
