package com.simao.giao.controller;

import com.simao.giao.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetController {

    AtomicLong atomicLong = new AtomicLong();

    @RequestMapping("/greet")
    public Greeting greet(@RequestParam(value = "name", defaultValue = "world") String name) {
        return new Greeting(atomicLong.incrementAndGet(), name);
    }
}
