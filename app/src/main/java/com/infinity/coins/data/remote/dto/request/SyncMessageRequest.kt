package com.infinity.coins.data.remote.dto.request

import androidx.annotation.Keep

@Keep
data class SyncMessageRequest(
    val messages: List<Message>
) {
    @Keep
    data class Message(
        val id: Int,
        val dateTime: Long,
        val sender: String,
        val body: String
    )
}