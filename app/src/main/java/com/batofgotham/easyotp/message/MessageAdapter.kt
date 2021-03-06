package com.batofgotham.easyotp.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batofgotham.easyotp.databinding.MessageListItemBinding

class MessageAdapter : ListAdapter<Message, MessageAdapter.MessageViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(MessageListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Message>(){
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.content == newItem.content
        }
    }

    class MessageViewHolder(private val binding : MessageListItemBinding) : RecyclerView.ViewHolder(binding.root){

        init{
            binding.setClickListener{ view->
                binding.message?.content.let{ message->
                    navigateToDetailFragment(message,view)
                }
            }
        }

        private fun navigateToDetailFragment(message: String?,view : View){
            view.findNavController().navigate(MessageFragmentDirections.actionMessageFragmentToMessageDetailFragment())
        }


        fun bind(message: Message){
            binding.message = message
            binding.executePendingBindings()
        }
    }
}