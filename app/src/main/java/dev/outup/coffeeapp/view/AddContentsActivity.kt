package dev.outup.coffeeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import dev.outup.coffeeapp.R
import dev.outup.coffeeapp.domain.model.Content

class AddContentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contents)
    }
}