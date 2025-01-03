package com.example.imagegenerator.data.model

sealed class Chat {
    data class IncomingMessage(val message: String) : Chat()
    data class OutgoingMessage(val message: String) : Chat()
}