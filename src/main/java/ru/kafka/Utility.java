package ru.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Utility {

	@Value("${server.port}")
	private String port;
	
	
	public void sendInfoModelToConfig(String configURL)
	{
		    RestTemplate restTemplate = new RestTemplate();
		    MicroserviceInfo model=new MicroserviceInfo();
		    model.setMicroserviceName(MicroservicesEnum.USERANDGROUPS);
		    model.setPort(this.port);
		  //  model.setToken(jwtTokenProvider.createTokenForMicroservice());
		    System.out.println(model.toString());
			HttpEntity<MicroserviceInfo> entity = new HttpEntity<MicroserviceInfo>(model);
			ResponseEntity<MicroserviceInfo> response = restTemplate.exchange(configURL,HttpMethod.POST,entity, MicroserviceInfo.class );
	}
}