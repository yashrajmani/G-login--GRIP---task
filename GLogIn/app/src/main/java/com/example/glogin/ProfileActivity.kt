package com.example.glogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.glogin.databinding.ActivityMainBinding
import com.example.glogin.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebase
        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()

        }
    }

    private fun checkUser() {
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser==null)
        {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        else
        {
            val email=firebaseUser.email
            val uid=firebaseUser.displayName
            binding.emailTv.text=email
            binding.uidTv.text=uid
        }
    }
}