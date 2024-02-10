package ru.voronezhtsev.phonebook.entity

import jakarta.persistence.*

@Entity
@Table(name = "phones")
class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var number: String? = null

    @ManyToOne
    @JoinColumn(name = "contact_id")
    var contact: Contact? = null

}