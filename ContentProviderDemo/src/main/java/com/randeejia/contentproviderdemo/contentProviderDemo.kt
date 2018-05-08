package com.randeejia.contentproviderdemo

import android.content.ContentProviderOperation
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast


class MessageManager(context:Context){

    var mContext = context

    val URI =  Uri.parse("content://sms")

    fun getMessages(){

        //获取的那些列表信息
        var cursor = mContext.contentResolver.query(URI, arrayOf("address","date","type","body"),null,null,null)
        while (cursor.moveToNext()){
            var address = cursor.getString(0)
            var date = cursor.getString(1)
            var type = cursor.getString(2)
            var body = cursor.getString(3)

            Log.e("MainActivity",String.format(" 地址:%s,时间:%s,类型:%s,内容:%s",address,date,type,body))
        }
        cursor.close()
    }

    fun insertMessage(){

        var conValues = ContentValues()

        conValues.put("address","123456789")
        conValues.put("type","1")
        conValues.put("date",System.currentTimeMillis())
        conValues.put("body","no zuo no die why you try")

        mContext.contentResolver.insert(URI,conValues)

        Log.e("MainActivity","短信插入完毕")
    }
}

class ContactManager(context: Context){

    var mContext = context

    val URI =  ContactsContract.CommonDataKinds.Phone.CONTENT_URI

    fun getContacts(){
        var cursor = mContext.contentResolver.query(URI,null,null,null,null)

        while (cursor.moveToNext()){
            var cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            var cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            var cEmail = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME))

            Log.e("MainActivity",String.format("姓名:%s,手机号码:%s,电子邮件:%s",cName,cNum,cEmail))
        }

        cursor.close()

    }


    fun addContact(){
        var uri = Uri.parse("content://com.android.contacts/raw_contacts")
        var dataUri = Uri.parse("content://com.android.contacts/data")

        var operations = ArrayList<ContentProviderOperation>()


        var op1 = ContentProviderOperation.newInsert(uri).withValue("account_name",null).build()

        operations.add(op1)

        var op2 = ContentProviderOperation.newInsert(dataUri).withValueBackReference("raw_contact_id",0)
                .withValue("mimetype","vnd.android.cursor.item/name")
                .withValue("data2","Coder-pig")
                .build()

        operations.add(op2)

        var op3= ContentProviderOperation.newInsert(dataUri).withValueBackReference("raw_contact_id",0)
                .withValue("mimetype","vnd.android.cursor.item/phone_v2")
                .withValue("data1","18611778263")
                .withValue("data2","2")
                .build()
        operations.add(op3)

        var op4= ContentProviderOperation.newInsert(dataUri).withValueBackReference("raw_contact_id",0)
                .withValue("mimetype","vnd.android.cursor.item/email_v2")
                .withValue("data1","randee_jia@163.com")
                .withValue("data2","2")
                .build()
        operations.add(op4)

        mContext.contentResolver.applyBatch("com.android.contacts",operations)

        Toast.makeText(mContext,"添加成功",Toast.LENGTH_LONG).show()
    }
}


