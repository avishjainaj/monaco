package com.ecommerce.monaco.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
public class ApplicationHealthController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationHealthController.class);

    @GetMapping("/health")
    public ResponseEntity health(){
        return ResponseEntity.ok("Application is running");
    }
}
