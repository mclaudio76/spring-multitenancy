package mclaudio76.application.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,XADataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class},scanBasePackages= {"it.liminf.core"})
@EnableTransactionManagement
public class Application  {
 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	} 
}