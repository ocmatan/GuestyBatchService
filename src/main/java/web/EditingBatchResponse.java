package web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.SingleRequest;

import java.util.ArrayList;
import java.util.List;

public class EditingBatchResponse {

    public List<SingleActionResponse> singleActionResponses = new ArrayList<>();

    public void addResponse(ResponseEntity<JsonNode> responseEntity, SingleRequest singleRequest){
        SingleActionResponse singleActionResponse = new SingleActionResponse();
        if(responseEntity != null){
            singleActionResponse.httpStatus = responseEntity.getStatusCode();
            if(singleActionResponse.httpStatus.isError()){
                singleActionResponse.description = responseEntity.getBody().asText();
            }
        }
        singleActionResponse.url = singleRequest.url;
        singleActionResponse.verb = singleRequest.httpMethod;
        singleActionResponses.add(singleActionResponse);
    }

    public class SingleActionResponse{

        public String url;
        public HttpMethod verb;
        public HttpStatus httpStatus;
        public String description;

    }

}
