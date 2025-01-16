package com.example.imagegenerator.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagegenerator.data.model.Chat
import dagger.hilt.android.lifecycle.HiltViewModel

class ChatViewModel : ViewModel() {
    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    init {
        _chats.value = mutableListOf()
    }

    fun addChat(chat: Chat) {
        val updatedList = _chats.value.orEmpty().toMutableList()
        updatedList.add(chat)
        _chats.value = updatedList
    }
}