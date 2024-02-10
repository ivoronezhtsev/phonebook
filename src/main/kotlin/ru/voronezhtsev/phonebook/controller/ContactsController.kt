package ru.voronezhtsev.phonebook.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.voronezhtsev.phonebook.service.ContactsService

@Controller
class ContactsController(private var contactsService: ContactsService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("contacts", contactsService.findAll())
        return "index"
    }
}