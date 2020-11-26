package br.com.ottimizza.statusreportapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${salesforce.service.name}", url = "${salesforce.service.url}")
public interface SalesforceClient {
    
    @GetMapping(value = "/api/v1/salesforce/execute_soql", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<?> getQuery(@RequestParam("soql") String soql,
                                  @RequestParam("methodExecution") int methodExecution,
                                  @RequestHeader("Authorization") String authorization);
}
