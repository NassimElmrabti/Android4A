package com.example.android4a.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R


class newListActivity : AppCompatActivity() {
     var recyclerView: RecyclerView? = null
     var mAdapter: listAdapter? = null
     var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recycler_view) as RecyclerView

        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!!.setLayoutManager(layoutManager)
        val input: MutableList<String> = ArrayList()
        for (i in 1..10) {
            input.add("Test$i")
        } // define an adapter

        mAdapter = listAdapter(input)
        recyclerView!!.setAdapter(mAdapter)

    }

}