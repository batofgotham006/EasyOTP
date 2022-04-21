package com.batofgotham.easyotp.message

import android.Manifest
import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.batofgotham.easyotp.R
import com.batofgotham.easyotp.databinding.FragmentMessageBinding
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog

class MessageFragment : Fragment(),EasyPermissions.PermissionCallbacks {

    private val messagesList: MutableList<Message> = mutableListOf()
    private lateinit var viewModel: MessageViewModel

    companion object{
        const val SMS_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMessageBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_message,container,false)



        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        binding.viewModel = viewModel

        requestSmsPermission()
        getMessages()

        val adapter = MessageAdapter()
        adapter.submitList(messagesList)
        binding.messagesRv.adapter = adapter

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    private fun hasSmsPermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS
        )

    private fun requestSmsPermission(){
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.permission_sms_read_and_receive_message),
            SMS_PERMISSION_REQUEST_CODE,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            SettingsDialog.Builder(requireActivity()).build().show()
        }
        else{
            requestSmsPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        getMessages()
    }

    private fun getMessages(){
        if(hasSmsPermission()){
            val cursor:Cursor = requireActivity().contentResolver.query(Telephony.Sms.CONTENT_URI,null,null,null,null)!!

            if(cursor.moveToFirst()){
                var i:Int = 0
                while(i<cursor.getCount()){

                    val person: String = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.CREATOR))
                    val body: String = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))
                    val message = Message(person,body)
                    messagesList.add(message)
                    cursor.moveToNext()
                    i++
                }

//                }while (cursor.moveToNext())
            }
        }

        Log.i("MSGSAAAA",messagesList.toString())
    }


}