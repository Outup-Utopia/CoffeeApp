package dev.outup.coffeeapp.infrastructure

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.cardview.widget.CardView
import dev.outup.coffeeapp.domain.model.Content

class ContentAdapter(private val context: Context, private val contents: List<Content>) : BaseAdapter() {
    override fun getCount(): Int {
        return contents.size
    }

    override fun getItem(position: Int): Content {
        return contents[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        return CardView(context)
    }
}