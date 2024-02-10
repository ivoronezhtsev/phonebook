package ru.voronezhtsev.phonebook.security

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false, unique = true)
    var username: String? = null

    val password: String? = null

}