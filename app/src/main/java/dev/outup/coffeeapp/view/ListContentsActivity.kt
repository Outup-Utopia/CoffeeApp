package dev.outup.coffeeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import dev.outup.coffeeapp.R
import dev.outup.coffeeapp.domain.model.Content
import dev.outup.coffeeapp.infrastructure.ContentAdapter

class ListContentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_contents)

        val contents = listOf(Content())

        val listView : ListView = findViewById(R.id.content_list)

        val adapter: ListAdapter = ContentAdapter(this, contents)
    }
}