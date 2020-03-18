package br.com.ottimizza.statusreportapi.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class IndexController {

    @GetMapping(value = {"index"})
    public ResponseEntity<?> getMethodName() {
        return ResponseEntity.ok("H3LL0 W0RLD!");
    }
}