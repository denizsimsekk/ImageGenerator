package com.example.imagegenerator.presentation.chat.adapter

import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.bumptech.glide.Glide
import com.example.imagegenerator.R
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.IncomingChatItemBinding

class IncomingChatViewHolder(val binding: IncomingChatItemBinding) :
    BaseViewHolder<Chat.IncomingMessage>(binding.root) {
    override fun bind(item: Chat.IncomingMessage) {
        binding.imageResponse.load(item.message) {
            crossfade(true)
            placeholder(R.drawable.progress)
        }
    }
}