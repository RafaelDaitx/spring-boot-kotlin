package br.com.daitiks

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping("/greeting")//Passa por parÂmetro o texto, se não o default é "World"
    fun greeting(@RequestParam(value="name", defaultValue = "World") name: String?): Greeting{
        return Greeting(counter.incrementAndGet(),"Hello, $name")
    }
}