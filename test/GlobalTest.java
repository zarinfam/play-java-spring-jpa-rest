import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by saeed on 9/March/15 AD.
 */
public class GlobalTest extends Global{
    @Override
    public void onStart(Application app) {
        applicationContext = new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

}
