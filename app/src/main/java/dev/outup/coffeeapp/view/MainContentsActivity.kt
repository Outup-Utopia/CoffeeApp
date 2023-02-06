package dev.outup.coffeeapp.view

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.outup.coffeeapp.MainActivity
import dev.outup.coffeeapp.R
import dev.outup.coffeeapp.domain.usecase.ContentService
import dev.outup.coffeeapp.infrastructure.CustomAdapter
import dev.outup.coffeeapp.infrastructure.repository.CoffeeRepositoryImpl
import dev.outup.coffeeapp.infrastructure.repository.ContentRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

class MainContentsActivity : AppCompatActivity() {
    private val auth: FirebaseAuth = Firebase.auth
    private val contentService = ContentService(ContentRepositoryImpl, CoffeeRepositoryImpl)
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_contents)

        auth.currentUser ?: run {
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        val applicationContext = this.applicationContext
        scope.async {
            val contents = contentService.getTimeline(auth.currentUser!!.uid)
            Log.d(ContentValues.TAG, "表示対象コンテンツ:")
            Log.d(ContentValues.TAG, contents.toString())
            val adapter = CustomAdapter(contents, applicationContext)
            val recyclerView = findViewById<RecyclerView>(R.id.content_recycler)
            recyclerView.adapter = adapter
        }
    }
}