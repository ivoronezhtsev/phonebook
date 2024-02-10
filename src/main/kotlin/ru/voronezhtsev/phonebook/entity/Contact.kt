package ru.voronezhtsev.phonebook.entity

import jakarta.persistence.*

@Entity
@Table(name = "contacts")
class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    @OneToMany(mappedBy = "contact", cascade = [CascadeType.ALL], orphanRemoval = true)
    var phones: MutableList<Phone> = mutableListOf()
}