package com.example.imagegenerator.presentation.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.FragmentChatBinding
import com.example.imagegenerator.presentation.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private lateinit var adapter: ChatAdapter
    private lateinit var binding: FragmentChatBinding
    private val viewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chatsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        adapter = ChatAdapter()
        binding.chatsRecyclerView.adapter=adapter
        binding.apply {
            sendButton.setOnClickListener {
                if (!promptEdittext.text.isNullOrEmpty()) {
                    viewModel.addChat(Chat.OutgoingMessage(promptEdittext.text.toString()))
                    promptEdittext.setText(" ")
                }
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.chats.observe(viewLifecycleOwner) {
            adapter.setChats(it)
        }
    }

}