package com.husaynhakeem.androidq_playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.husaynhakeem.androidq_playground.data.FeaturesDataSource
import com.husaynhakeem.androidq_playground.utilities.computeSpanCount
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        mainRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, computeSpanCount(window))
            setHasFixedSize(true)
            adapter = MainAdapter().apply { submitList(FeaturesDataSource.getAllFeatures()) }
        }
    }
}
