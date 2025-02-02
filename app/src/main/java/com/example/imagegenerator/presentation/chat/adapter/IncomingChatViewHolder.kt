package com.example.imagegenerator.presentation.chat.adapter

import com.bumptech.glide.Glide
import com.example.imagegenerator.R
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.IncomingChatItemBinding

class IncomingChatViewHolder(val binding: IncomingChatItemBinding) :
    BaseViewHolder<Chat.IncomingMessage>(binding.root) {
    override fun bind(item: Chat.IncomingMessage) {
        Glide.with(binding.root.context).load(item.message).placeholder(R.drawable.progress)
            .into(binding.imageResponse)
    }
}