package com.project.configuration;

import org.springframework.context.annotation.Configuration;



import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.model.Authorities;
import com.project.model.BillingAddress;
import com.project.model.Cart;
import com.project.model.CartItem;
import com.project.model.Category;
import com.project.model.Customer;
import com.project.model.Product;
import com.project.model.ShippingAddress;
import com.project.model.User;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

@Configuration
@EnableTransactionManagement   //commit / rollback
public class DBConfiguration {
	//to create beans
	
		@Bean(name="dataSource")
		public DataSource getDataSource() {
			System.out.println("Entering DataSource Bean creation method ");
		    BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("org.h2.Driver");
		    dataSource.setUrl("jdbc:h2:~/test1");
		    dataSource.setUsername("aaaaa");
		    dataSource.setPassword("bbbbb");
		    System.out.println("DataSource bean " +dataSource);
		    return dataSource;
		}
		/* 
		 * <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBuilder">
		 * <property name="dataSource" ref="dataSource">
		 */
		@Bean //SessionFactory - factory of session objects
		public SessionFactory sessionFactory() {
			System.out.println("Entering sessionFactory creation method");
			LocalSessionFactoryBuilder lsf=
					new LocalSessionFactoryBuilder(getDataSource());
			Properties hibernateProperties=new Properties();
			hibernateProperties.setProperty(
					"hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			hibernateProperties.setProperty("hibernate.show_sql", "true");
			lsf.addProperties(hibernateProperties);
			//An array of Class objects of all the entities
			//Map all entities to relational table
			Class classes[]=new Class[]{Product.class, Category.class, User.class, ShippingAddress.class, Customer.class, CartItem.class, Cart.class, BillingAddress.class, Authorities.class};
			//localsesionfactorybuilder -> sessionfactory -> map all entities with relation table
			System.out.println("Entering sessionFactory creation method");
		    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
		}
		@Bean
		public HibernateTransactionManager hibTransManagement(){
			return new HibernateTransactionManager(sessionFactory());
		}
}
