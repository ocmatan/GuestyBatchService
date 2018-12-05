package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;
import web.ActionRequestParams;
import web.SingleActionBatchRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestProcessor {

    @Autowired
    RequestHandler requestHandler;

    public void process(List<SingleActionBatchRequest> requests){
        System.out.println("processing");
        for(SingleActionBatchRequest singleActionBatchRequest : requests){
            for(ActionRequestParams actionRequestParams : singleActionBatchRequest.objects){
                SingleRequest singleRequest = new SingleRequest();
                singleRequest.url = buildUri(singleActionBatchRequest.url, actionRequestParams.query);
                singleRequest.httpMethod = singleActionBatchRequest.verb;
                singleRequest.body =  actionRequestParams.data;
                requestHandler.handleRequest(singleRequest);
            }
        }
    }


    private String buildUri(String templateUri, JsonNode query){
        UriTemplate template = new UriTemplate(templateUri);
        Map<String, String> uriVariables = new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.convertValue(query, Map.class);
        for(String field : jsonMap.keySet()){
            uriVariables.put(field, jsonMap.get(field).toString());
        }
        return template.expand(uriVariables).toASCIIString();
    }

}
