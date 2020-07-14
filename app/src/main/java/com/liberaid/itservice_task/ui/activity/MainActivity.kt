package com.liberaid.itservice_task.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liberaid.itservice_task.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
