package com.example.mytestapp.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.databinding.ItemRowBinding


class AdapterAttentWork () :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEWTYPE_ROW = 1
    }

    var data: List<UserEntity> = listOf()


    fun loadData(content:List<UserEntity>) {
        this.data = content
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEWTYPE_ROW -> {
                val view = ItemRowBinding.inflate(inflater, parent, false)
                ViewHolder(view)
            }
            else -> {
                val view = ItemRowBinding.inflate(inflater, parent, false)
                ViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (holder) {
            is ViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data:UserEntity) = with(binding) {
            Id.text = data.id.toString()
            textEmployeeId.text = data.employeeID
            textField2.text = data.message
        }
    }
}

