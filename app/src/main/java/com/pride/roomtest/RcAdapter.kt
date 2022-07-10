package com.pride.roomtest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pride.roomtest.databinding.ItemBinding


class RcAdapter : RecyclerView.Adapter<RcAdapter.ItemHolder>() {

    private var listName = ArrayList<Name>()

    class ItemHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ItemBinding.bind(itemView)
        fun bind(name: Name) = with(binding) {
            textId.text = name.id.toString()
            textName.text = name.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listName.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(listName[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(noteslist: ArrayList<Name>) {
        listName.clear()
        listName.addAll(noteslist)
        notifyDataSetChanged()
    }
}