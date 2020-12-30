package com.example.android4a.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.presentation.model.FinalFantasy
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject

class newListActivity : AppCompatActivity() {
     var recyclerView: RecyclerView? = null
     var mAdapter: listAdapter? = null
     var layoutManager: RecyclerView.LayoutManager? = null
     val listViewModel: listViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        listViewModel.makeApiCall()

        listViewModel.apiLiveData.observe(this, Observer {
           when (it) {
               is ApiStatusSuccess -> {
                   mAdapter = listAdapter(it.FFList as MutableList<FinalFantasy>)
                   recyclerView!!.adapter = mAdapter

                   Log.d("Success", "API SUCCESS")
               }

               is ApiStatusError -> {
                   MaterialAlertDialogBuilder(this)
                       .setTitle("Error")
                       .setMessage("API call failed")
                       .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
                       .show()
                   Log.d("ERROR", "API ERROR")

               }
           }
       })
    }

}