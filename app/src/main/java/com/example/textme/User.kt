package com.example.textme

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
class User(
    val id: Long,
    val username: String,
    val tag: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val created_at: LocalDateTime
    ) {
}