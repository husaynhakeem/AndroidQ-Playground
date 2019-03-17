package com.husaynhakeem.sharingshortcut

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.husaynhakeem.sharingshortcut.data.Droid
import com.husaynhakeem.sharingshortcut.data.DroidDataSource
import com.husaynhakeem.sharingshortcut.utilities.EXTRA_DROID_ID
import kotlinx.android.synthetic.main.sharingshortcut_activity_select_droid.*
import kotlinx.android.synthetic.main.sharingshortcut_item_droid.view.*


class SelectDroidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sharingshortcut_activity_select_droid)
        setupView()
    }

    private fun setupView() {
        selectDroidRecyclerView.layoutManager = LinearLayoutManager(this)
        selectDroidRecyclerView.adapter = DroidsAdapter(DroidDataSource.getAllDroids())
        selectDroidRecyclerView.setHasFixedSize(true)
    }

    inner class DroidsAdapter(private val droids: List<Droid>) :
        RecyclerView.Adapter<DroidsAdapter.SelectDroidViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SelectDroidViewHolder(parent)

        override fun onBindViewHolder(holder: SelectDroidViewHolder, position: Int) = holder.render(droids[position])

        override fun getItemCount() = droids.size

        inner class SelectDroidViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.sharingshortcut_item_droid, parent, false)
        ) {

            fun render(droid: Droid) {
                itemView.itemDroidTextView.text = droid.name
                val drawable = ContextCompat.getDrawable(itemView.context, droid.image)
                itemView.itemDroidTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
                itemView.setOnClickListener {
                    val data = Intent()
                    data.putExtra(EXTRA_DROID_ID, droid.id)
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }
            }
        }
    }
}