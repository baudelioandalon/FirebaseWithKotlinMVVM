package com.baudelioandalon.firebasewithkotlinmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baudelioandalon.firebasewithkotlinmvvm.R
import com.baudelioandalon.firebasewithkotlinmvvm.ui.adapters.mainAdapter
import com.baudelioandalon.firebasewithkotlinmvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: mainAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter =
            mainAdapter(
                this
            )
        lifecycle.addObserver(viewModel)
        recyclerView.layoutManager =  LinearLayoutManager(this)
        recyclerView.adapter  = adapter
        observeData()
    }

    fun observeData(){
        viewModel.getData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}
