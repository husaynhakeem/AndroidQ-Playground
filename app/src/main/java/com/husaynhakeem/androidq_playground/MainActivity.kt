package com.husaynhakeem.androidq_playground

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        mainRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, getSpanCount())
            setHasFixedSize(true)
            adapter = MainAdapter().apply { submitList(FeaturesDataSource.get()) }
        }
    }

    private fun getSpanCount(): Int {
        val metrics = DisplayMetrics()
        window.windowManager.defaultDisplay.getMetrics(metrics)
        val widthInDP = metrics.widthPixels / metrics.density
        val spanCountWithMinWidth = (widthInDP / 200).toInt()
        return Math.max(2, spanCountWithMinWidth)
    }
}
