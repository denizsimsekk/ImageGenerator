package com.example.imagegenerator.presentation.chat.adapter

import android.app.Dialog
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.Window
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.example.imagegenerator.R
import com.example.imagegenerator.data.model.Chat
import com.example.imagegenerator.databinding.ImageDialogLayoutBinding
import com.example.imagegenerator.databinding.IncomingChatItemBinding

class IncomingChatViewHolder(
    val binding: IncomingChatItemBinding,
    val onDownloadClick: ((String) -> Unit)?
) :
    BaseViewHolder<Chat.IncomingMessage>(binding.root) {

    override fun bind(item: Chat.IncomingMessage) {
        binding.imageResponse.load(item.message) {
            crossfade(true)
            placeholder(R.drawable.ic_progress)
            error(R.drawable.baseline_error_24)
            listener(
                onSuccess = { _, _ ->
                    binding.imageResponse.setOnClickListener {
                        showImageDialog(item.message)
                    }
                }
            )
        }
    }

    private fun showImageDialog(reply: String) {
        val dialog = Dialog(binding.root.context)

        val inflater: LayoutInflater = LayoutInflater.from(binding.root.context)

        val dialogBinding = ImageDialogLayoutBinding.inflate(inflater)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.generatedImage.load(reply) {
            crossfade(true)
            placeholder(R.drawable.ic_progress)
            error(R.drawable.baseline_error_24)
        }

        dialogBinding.download.setOnClickListener {
            onDownloadClick?.invoke(reply)
        }

        dialogBinding.close.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }
}