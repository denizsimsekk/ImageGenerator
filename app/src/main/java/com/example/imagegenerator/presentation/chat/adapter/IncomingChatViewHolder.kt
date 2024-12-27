package com.example.imagegenerator.presentation.chat.adapter

import com.bumptech.glide.Glide
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.IncomingChatItemBinding

class IncomingChatViewHolder(val binding:IncomingChatItemBinding) :BaseViewHolder<Chat>(binding.root) {
    override fun bind(item: Chat) {
        Glide.with(binding.root.context).load(item.chat?:"").into(binding.imageResponse)
    }
}