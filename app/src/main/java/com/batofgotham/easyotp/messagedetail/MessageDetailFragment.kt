package com.batofgotham.easyotp.messagedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.batofgotham.easyotp.R
import com.batofgotham.easyotp.databinding.FragmentMessageDetailBinding

class MessageDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMessageDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_message_detail,container,false)



        return binding.root
    }

}