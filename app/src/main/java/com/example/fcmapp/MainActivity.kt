package com.example.fcmapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.fcmapp.databinding.ActivityMainBinding
import com.example.fcmapp.viewmodel.NotificationViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: NotificationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.sendBtn.setOnClickListener {
            val title = binding.messageTitle.text.toString().trim()
            val body = binding.messageBody.text.toString().trim()


            if (title.isNotEmpty() && body.isNotEmpty()) {
                viewModel.sendNotification(title, body)
            } else {
                Toast.makeText(
                    this,
                    "Please enter message titlr and message body",
                    Toast.LENGTH_SHORT
                ).show()
            }
            viewModel.notificationSent.observe(this, Observer {
                if (it) {
                    Toast.makeText(this, "Notification sent successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            })


            viewModel.errorMessage.observe(this, Observer {
                it.let {
                    Toast.makeText(this, "Error $it", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}