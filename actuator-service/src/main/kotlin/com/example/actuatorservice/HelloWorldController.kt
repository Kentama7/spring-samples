package com.example.actuatorservice

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.concurrent.atomic.AtomicLong

@Controller
class HelloWorldController {
    companion object {
        private const val template = "Hello, %s!"
    }

    private val counter = AtomicLong()

    @GetMapping("/hello-world")
    @ResponseBody
    fun sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") name: String): Greeting =
        Greeting(counter.incrementAndGet(), String.format(template, name))
}
