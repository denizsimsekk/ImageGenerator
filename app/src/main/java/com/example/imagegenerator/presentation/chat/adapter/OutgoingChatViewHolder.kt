package com.example.imagegenerator.presentation.chat.adapter

import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.OutgoingChatItemBinding

class OutgoingChatViewHolder(val binding: OutgoingChatItemBinding) : BaseViewHolder<Chat.OutgoingMessage>(binding.root) {
    override fun bind(item: Chat.OutgoingMessage) {
        binding.promptText.text=item.message
    }
}