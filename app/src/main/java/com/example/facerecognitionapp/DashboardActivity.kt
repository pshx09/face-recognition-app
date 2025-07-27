package com.example.facerecognitionapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var uploadButton: Button
    private lateinit var scanButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var userNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()
        userNameTextView = findViewById(R.id.userNameTextView)
        uploadButton = findViewById(R.id.uploadButton)
        scanButton = findViewById(R.id.scanButton)

        val user = auth.currentUser
        userNameTextView.text = "Welcome, ${user?.displayName}"

        uploadButton.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }

        scanButton.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
    }
}
