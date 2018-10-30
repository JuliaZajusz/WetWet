package com.wetwet.ReservationService.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class HelloWorldController {

    @GetMapping()
    String getHello(){
        return "Hello";
    }
}
