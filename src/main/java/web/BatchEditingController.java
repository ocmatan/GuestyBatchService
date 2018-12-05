package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import service.RequestProcessor;

import java.util.List;

@RestController
public class BatchEditingController {

    @Autowired
    RequestProcessor requestProcessor;

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    public String batch(@RequestBody List<SingleActionBatchRequest> requests) {
        requestProcessor.process(requests);
        return "Greetings from Spring Boot!";
    }

}