package ru.voronezhtsev.phonebook.service

import org.springframework.stereotype.Service
import ru.voronezhtsev.phonebook.entity.Contact
import ru.voronezhtsev.phonebook.entity.Phone
import ru.voronezhtsev.phonebook.model.ContactModel
import ru.voronezhtsev.phonebook.repository.ContactsRepository

@Service
class ContactsService(private var contactsRepository: ContactsRepository) {
    fun findAll() = contactsRepository.findAll()

    fun save(model: ContactModel) {
        val contact = Contact()
        contact.firstName = model.firstName
        contact.lastName = model.lastName
        val phone = Phone()
        phone.contact = contact
        phone.number = model.number
        contact.phones = mutableListOf(phone)
        contactsRepository.save(contact)
    }
}