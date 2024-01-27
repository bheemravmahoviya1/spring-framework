package spring.core.beanscope.resource;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.print.attribute.standard.Media;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import spring.core.beanscope.beans.TeaCoffee;
import spring.core.beanscope.beans.WorkTime;
import spring.core.beanscope.beans.Company;
import spring.core.beanscope.beans.Employee;
import spring.core.beanscope.beans.Project;

@Slf4j
@RestController

public class BeanResource implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
		log.info("ApplicationContextAware.setApplicationContext(-) application context ");
		
	}
	
	@GetMapping("/welcome")
	public String welcomeMsg() {
		log.warn("BeanResource.welcomeMsg(-) welcome ");
		
		return "Welcome new my Baby to the spring scope. This is just begining with spring scope.";
	}

	
	/** 
	 * Note: Verify for each getBean(-) method call it should give a same bean. 
	 * */
	@GetMapping(value="/singleton")
	public String getSingletonScope() {

		Project project = applicationContext.getBean(Project.class);
		System.out.println("BeanResource.getSingletonScope(-) is project bean found? "+project);
		System.out.println("BeanResource.getSingletonScope(-) is same project bean found second time? "+applicationContext.getBean(Project.class));
		System.out.println("BeanResource.getSingletonScope(-) is same project bean found third time? "+applicationContext.getBean(Project.class));
		
		String msg = Objects.nonNull(project) ? "Bean is found" : "Bean is not found";
		return msg ;
	}
	
	
	/** 
	 * Note: Verify for each getBean(-) method call it should give a unique bean. 
	 * */ 
	@GetMapping("/prototype")
	public String getPrototypeScope() {
		Employee employee = applicationContext.getBean(Employee.class);
		System.out.println("BeanResource.getPrototypeScope(-) is employee bean found? "+employee);
		System.out.println("BeanResource.getPrototypeScope(-) is new employee bean found second time? "+applicationContext.getBean(Employee.class));
		System.out.println("BeanResource.getPrototypeScope(-) is new employee bean found third time? "+applicationContext.getBean(Employee.class));
		String msg = Objects.nonNull(employee) ? "Bean is found" : "Bean is not found";
		return msg ;
	}
	
	
	/**
	 * Note: Please verify that are you getting new bean for each request? if yes 
	 * then you are good else learn some more on this concept.
	 */
	@GetMapping("/request")
	public String getRequestScope() {
		TeaCoffee teaCoffee = applicationContext.getBean(TeaCoffee.class);
		System.out.println("--------------------- New request  -------------------------- Thread Name: "+Thread.currentThread().getName());
		System.out.println("BeanResource.getRequestScope(-) is teaCoffee bean found? "+teaCoffee+" value is : "+teaCoffee.getTea());
		teaCoffee.setTea("tea with milk");
		System.out.println("BeanResource.getRequestScope(-) teaCoffee bean value change to 'tea with milk' : "+teaCoffee+" value is : "+teaCoffee.getTea());
		
		System.out.println("BeanResource.getRequestScope(-) is same teaCoffee bean found second time? "+applicationContext.getBean(TeaCoffee.class)+" value is : "+teaCoffee.getTea());
		System.out.println("BeanResource.getRequestScope(-) is same teaCoffee bean found third time? "+applicationContext.getBean(TeaCoffee.class)+" value is : "+teaCoffee.getTea());
		String msg = Objects.nonNull(teaCoffee) ? "Bean is found" : "Bean is not found";
		return msg ;
	}
	
	/** 
	 * Note: To test the session scope two browser in incognito mode 
	 * and you will find that for each browser new bean created" 
	 * 
	 * */
	@GetMapping("/session")
	public String getSessionScope() {
		WorkTime worktime = applicationContext.getBean(WorkTime.class);
		String timeFormate = " %s in time : %s  out time : %s";
		System.out.println("--------------------- New request  -------------------------- Thread Name: "+Thread.currentThread().getName());
		System.out.println("BeanResource.getSessionScope(-) is worktime bean found with only in timing? "+String.format(timeFormate,worktime, worktime.getInTime(),worktime.getOutTime()));
		worktime.setOutTime(LocalDateTime.now().plusHours(8));
		System.out.println("BeanResource.getSessionScope(-) is teaCoffee bean found with both in and out timing ? "+String.format(timeFormate,worktime, worktime.getInTime(),worktime.getOutTime()));
		String msg = Objects.nonNull(worktime) ? "Bean is found" : "Bean is not found";
		return msg ;
	}
	
	
	/** 
	 * Note: TODO to be verify it.
	 * 
	 * */
	@GetMapping("/application")
	public Company getApplication() {
		return applicationContext.getBean(Company.class);
	}
	
}
