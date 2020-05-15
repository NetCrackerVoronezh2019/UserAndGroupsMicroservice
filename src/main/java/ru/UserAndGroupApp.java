package ru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ru.kafka.ConsumerThreadService;
import ru.kafka.Utility;

@SpringBootApplication
public class UserAndGroupApp {
    public static void main(String[] args) {
    	ConfigurableApplicationContext app=SpringApplication.run(UserAndGroupApp.class, args);
		try {
				ConsumerThreadService threadService = (ConsumerThreadService) app.getBean("consumerThreadService");		
				Utility utility=(Utility) app.getBean("utility");	
			    utility.sendInfoModelToConfig("http://localhost:7082/setInfoModel");
			    Thread microserviceInfoThread =new Thread(threadService.microserviceInfoRunnable());
			    microserviceInfoThread.start();
			}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
    }
}
