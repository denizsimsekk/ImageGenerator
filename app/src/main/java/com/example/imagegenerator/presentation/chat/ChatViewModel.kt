package com.example.imagegenerator.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _chats = MutableLiveData<MutableList<Chat>>()
    val chats: LiveData<MutableList<Chat>> = _chats

    init {
        _chats.value = mutableListOf()
    }

    fun addChat(message: String) {
        viewModelScope.launch {
            if (_chats.value?.lastOrNull() !is Chat.OutgoingMessage) {
                val updatedList = _chats.value.orEmpty().toMutableList()
                val outgoingMessage = Chat.OutgoingMessage(message)
                updatedList.add(outgoingMessage)
                updatedList.add(Chat.IncomingMessage(outgoingMessage.message.toPrompt()))
                _chats.value = updatedList
            }
        }
    }

    fun String.toPrompt(): String =
        Constants.BASE_URL + this.replaceTurkishChars().replace(" ", "-")

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