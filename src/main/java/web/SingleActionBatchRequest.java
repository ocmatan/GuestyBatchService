package web;

import org.springframework.http.HttpMethod;

import java.util.List;

public class SingleActionBatchRequest {

    public String url;
    public HttpMethod verb;
    public List<ActionRequestParams> objects;

}
