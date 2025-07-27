package com.example.textme

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
class Message(
    val id: String,
    val username: String,
    val text: String,
    val created_at: LocalDateTime,
    val user_id: Long
    ) {
}