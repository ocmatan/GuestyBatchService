package service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class RequestHandler {

    RestTemplate restTemplate;

    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();
    }


    public ResponseEntity handleRequest(SingleRequest singleRequest){
        System.out.println("handling single request");
        ResponseEntity<JsonNode> response = null;
        try{
            HttpEntity<JsonNode> request = new HttpEntity<>(singleRequest.body);
            response = restTemplate.exchange(singleRequest.url, singleRequest.httpMethod, request, JsonNode.class);
        }catch (Exception e){

        }
        return response;
    }

}
