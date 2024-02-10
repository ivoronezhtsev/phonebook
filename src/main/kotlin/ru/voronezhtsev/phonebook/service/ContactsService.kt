package ru.voronezhtsev.phonebook.service

import org.springframework.stereotype.Service
import ru.voronezhtsev.phonebook.repository.ContactsRepository

@Service
class ContactsService(private var contactsRepository: ContactsRepository) {
    fun findAll() = contactsRepository.findAll()
}