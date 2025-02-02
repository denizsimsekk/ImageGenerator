package com.example.imagegenerator.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagegenerator.data.model.Chat
import dagger.hilt.android.lifecycle.HiltViewModel

class ChatViewModel : ViewModel() {
    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    val url = "https://image.pollinations.ai/prompt/"

    init {
        _chats.value = mutableListOf()
    }

    fun addChat(chat: Chat) {
        if (_chats.value!!.isEmpty() || _chats.value?.last() !is Chat.OutgoingMessage) {
            val updatedList = _chats.value.orEmpty().toMutableList()
            updatedList.add(chat)
            updatedList.add(Chat.IncomingMessage((chat as Chat.OutgoingMessage).message.toPrompt()))
            _chats.value = updatedList
        }
    }

    fun String.toPrompt(): String = url + this.replaceTurkishChars().replace(" ", "-")

    fun String.replaceTurkishChars(): String {
        val replacements = mapOf(
            'ç' to 'c', 'Ç' to 'C',
            'ğ' to 'g', 'Ğ' to 'G',
            'ı' to 'i', 'İ' to 'I',
            'ö' to 'o', 'Ö' to 'O',
            'ş' to 's', 'Ş' to 'S',
            'ü' to 'u', 'Ü' to 'U'
        )
        return buildString {
            for (char in this@replaceTurkishChars) {
                append(replacements[char] ?: char)
            }
        }
    }

}