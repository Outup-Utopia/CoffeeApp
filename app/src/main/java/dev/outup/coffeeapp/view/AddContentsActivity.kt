package dev.outup.coffeeapp.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import dev.outup.coffeeapp.R
import dev.outup.coffeeapp.domain.model.option.Appendable


class AddContentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contents)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        for (value in Appendable.values()) {
            adapter.add(value.name)
        }
        val spinner: Spinner = findViewById<View>(R.id.optionNameSpinner) as Spinner
        spinner.adapter = adapter
    }
}