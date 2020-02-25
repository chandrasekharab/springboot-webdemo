package com.example.SpringBootWebDemo

import com.example.data.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value="name")name:String) = User("cc", name)
}