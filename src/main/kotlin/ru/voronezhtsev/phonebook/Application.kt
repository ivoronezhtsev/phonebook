package ru.voronezhtsev.phonebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.voronezhtsev.phonebook.security.User

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
    var user = User()
    
}