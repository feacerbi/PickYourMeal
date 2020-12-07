package com.felipeacerbi.pickyourmeal.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.felipeacerbi.pickyourmeal.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
    }
}