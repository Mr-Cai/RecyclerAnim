package com.patrickiv.demo.enteranimationdemo.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.patrickiv.demo.enteranimationdemo.R

/**
 * Created by Patrick Ivarsson on 7/17/17.
 */
 class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_empty_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        val ITEM_COUNT = 64
    }

}
