package ru.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerThreadService {

	
	@Autowired
	private Microservices micro;
	
	private KafkaConsumer<String,String> microserviceInfoConsumer;

	@Value("${kafka.microserviceInfoTopic}")
	private String microserviceInfoTopic;
	
	@Value("${kafka.microservcieInfoGroup}")
	private String microserviceInfoGroup;
	
	@PostConstruct
	public void init()
	{
		try {
		String bootstrapServers1="192.168.99.103:9092";
    	Properties properties1=new Properties();
    	properties1.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers1);
    	properties1.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties1.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties1.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
    	properties1.setProperty(ConsumerConfig.GROUP_ID_CONFIG, this.microserviceInfoTopic);
    	this.microserviceInfoConsumer=new KafkaConsumer<String,String>(properties1);
    	this.microserviceInfoConsumer.subscribe(Arrays.asList(this.microserviceInfoGroup));
    	
		}
		catch(Exception ex) {}
	}
	
	public Runnable microserviceInfoRunnable()
	{
		return new Runnable() {
			
            public void run() {
            	try {	
        			while(true)
        	    	{
        				RestTemplate template=new RestTemplate();
        	    		ConsumerRecords<String,String> records=microserviceInfoConsumer.poll(Duration.ofMillis(100));	
        	    		ResponseEntity<List<MicroserviceInfo>> res=template.exchange("http://192.168.99.103:7082/getAllInfo",HttpMethod.GET,null,new ParameterizedTypeReference<List<MicroserviceInfo>>(){});
            			micro.setMicroservicesInfo(res.getBody());
        	    	}
        		}
        		
        		catch(WakeupException e)
        		{
        			System.out.println(e.getMessage()+" ------WakeupException");
        		}
        		finally
        		{
        			System.out.println("UNSUBSCRIBE");
        			microserviceInfoConsumer.unsubscribe();
        			microserviceInfoConsumer.close();
        			
        		}
            }
        };
	}
}
