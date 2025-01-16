package com.example.imagegenerator.presentation.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegenerator.R
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.IncomingChatItemBinding
import com.example.imagegenerator.databinding.OutgoingChatItemBinding

class ChatAdapter : RecyclerView.Adapter<BaseViewHolder<Chat>>() {

    private val chatList = arrayListOf<Chat>()

    fun setChats(list: List<Chat>) {
        chatList.clear()
        chatList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Chat> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            INCOMING_MESSAGE_VIEW_TYPE -> {
                val binding = IncomingChatItemBinding.inflate(layoutInflater, parent, false)
                IncomingChatViewHolder(binding)
            }

            OUTGOING_MESSAGE_VIEW_TYPE -> {
                val binding = OutgoingChatItemBinding.inflate(layoutInflater, parent, false)
                OutgoingChatViewHolder(binding)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun getItemCount(): Int = chatList.size

    override fun onBindViewHolder(holder: BaseViewHolder<Chat>, position: Int) {
        when (holder) {
            is IncomingChatViewHolder -> {
                holder.bind(chatList[position])
            }

            is OutgoingChatViewHolder -> {
                holder.bind(chatList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (chatList[position]) {
            is Chat.IncomingMessage -> INCOMING_MESSAGE_VIEW_TYPE
            is Chat.OutgoingMessage -> OUTGOING_MESSAGE_VIEW_TYPE
        }
    }

    companion object {
        private const val INCOMING_MESSAGE_VIEW_TYPE = 0
        private const val OUTGOING_MESSAGE_VIEW_TYPE = 1
    }

}