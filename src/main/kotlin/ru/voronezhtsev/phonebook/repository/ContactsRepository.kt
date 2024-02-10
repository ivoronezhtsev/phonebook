package ru.voronezhtsev.phonebook.repository

import org.springframework.data.repository.CrudRepository
import ru.voronezhtsev.phonebook.entity.Contact

interface ContactsRepository: CrudRepository<Contact, Long> {
}