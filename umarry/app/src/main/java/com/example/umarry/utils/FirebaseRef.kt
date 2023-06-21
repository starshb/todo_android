package com.example.umarry.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {
    companion object {
        val database = Firebase.database

        val memberRef = database.getReference("member")
    }
}