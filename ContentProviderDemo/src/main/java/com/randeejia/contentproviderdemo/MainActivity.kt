package com.randeejia.contentproviderdemo

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var msgManager = MessageManager(this)

        msgManager.insertMessage()

//        if (ContextCompat.checkSelfPermission(baseContext,"android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED){
//            msgManager.getMessages()
//        }

        msgManager.getMessages()


        var contactManager = ContactManager(this)

//        if (ContextCompat.checkSelfPermission(baseContext,"android.permission.READ_CONTACTS") == PackageManager.PERMISSION_GRANTED){
//            contactManager.addContact()
//
//            contactManager.getContacts()
//        }
        contactManager.addContact()
//
        contactManager.getContacts()

    }


}
