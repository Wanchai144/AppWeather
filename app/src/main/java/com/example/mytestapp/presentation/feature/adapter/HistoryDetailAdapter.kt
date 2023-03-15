package com.example.mytestapp.presentation.feature.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.*
import kotlinx.android.synthetic.main.item_icon.view.*


class HistoryDetailAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    var data: List<Coin> = arrayListOf()
        set(value) {
            val result = DiffUtil.calculateDiff(CategoryDiffCallback(field, value))
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = inflater.inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_icon, parent, false)
                ItemViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_HEADER) {
            val headerHolder = holder as HeaderViewHolder
            headerHolder.bind(data[position])
        } else {
            val itemHolder = holder as ItemViewHolder
            itemHolder.bind(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].bpi.isNullOrEmpty()) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(coin: Coin) {
            itemView.tvName.text = coin.time
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(coin: Coin) {
            for (data in coin.bpi!!) {
                itemView.tvName.text = data.code
                itemView.tvDescription.text = data.description
                itemView.tvRate.text = data.rate
                itemView.tvRateFloat.text = data.rate_float.toString()
            }
        }
    }

    inner class CategoryDiffCallback(private val o: List<Any>, private val n: List<Any>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = o.size

        override fun getNewListSize(): Int = n.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldObject = o[oldItemPosition]
            val newObject = n[newItemPosition]
            //if we want faster update we also need to implement getPayloadChange
            return if (oldObject is DataBpi && newObject is DataBpi) {
                oldObject.code == newObject.code && oldObject.rate == newObject.rate
            } else {
                o == n
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            o[oldItemPosition] == n[newItemPosition]
    }




}