package service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;

public class SingleRequest {

    public String url;
    public HttpMethod httpMethod;
    public JsonNode body;


}
