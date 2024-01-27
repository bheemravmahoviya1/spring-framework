package spring.core.beanscope.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import spring.core.beanscope.beans.TeaCoffee;
import spring.core.beanscope.beans.Employee;
import spring.core.beanscope.beans.Project;
import spring.core.beanscope.beans.WorkTime;

@Configuration
public class BeanConfigiration {

	@Bean
	@Scope("prototype")
	public Employee getEmployee() {
		return new Employee();
	}
	
	@Bean
	public Project getProject(){
		return new Project();
	}
	
	
	@Bean
	@RequestScope
	public TeaCoffee getDepartment() {
		return new TeaCoffee();
	}
	
	@Bean
	@SessionScope
	public WorkTime getWorktime() {
		return new WorkTime();
	}
	
	
}
