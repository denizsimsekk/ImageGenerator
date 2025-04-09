package com.example.imagegenerator.presentation.chat

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Rect
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.FragmentChatBinding
import com.example.imagegenerator.downloadmanager.AndroidDownloader
import com.example.imagegenerator.presentation.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.OutputStream


@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var adapter: ChatAdapter
    private lateinit var binding: FragmentChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var downloader: AndroidDownloader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chatsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ChatAdapter()
        downloader = AndroidDownloader(requireContext())
        adapter.setOnDownloadClickCallback { url ->
            downloader.downloadFile(url)
        }
        binding.chatsRecyclerView.adapter = adapter
        binding.apply {
            sendButton.setOnClickListener {
                if (!promptEdittext.text.isNullOrEmpty()) {
                    viewModel.addChat(promptEdittext.text.toString())
                    promptEdittext.setText("")
                }
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.chats.observe(viewLifecycleOwner) {
            if (it.isEmpty().not()) {
                setVisibility()
                adapter.setChats(it)
                binding.chatsRecyclerView.smoothScrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun setVisibility() {
        binding.apply {
            tvTitle.visibility = View.GONE
            giph.visibility = View.GONE
            chatsRecyclerView.visibility = View.VISIBLE
        }
    }

}