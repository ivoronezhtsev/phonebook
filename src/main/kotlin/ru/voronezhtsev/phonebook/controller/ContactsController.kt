package ru.voronezhtsev.phonebook.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.voronezhtsev.phonebook.entity.Contact
import ru.voronezhtsev.phonebook.model.ContactModel
import ru.voronezhtsev.phonebook.service.ContactsService

@Controller
class ContactsController(private var contactsService: ContactsService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("contacts", contactsService.findAll())
        return "index"
    }

    @GetMapping("/add")
    fun add(model: Model): String {
        model.addAttribute("contact", ContactModel())
        return "contact-info"
    }

    @PostMapping("/contacts")
    fun save(@ModelAttribute("contact") contact: ContactModel): String {
        contactsService.save(contact)
        return "redirect:/"
    }
}