package com.example.mytestapp.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataBpi
import kotlinx.android.synthetic.main.item_icon.view.*


class CoinDeskAdapter() :
    RecyclerView.Adapter<CoinDeskAdapter.FeatureViewHolder>() {

    var data: List<DataBpi> = arrayListOf()
        set(value) {
            val result = DiffUtil.calculateDiff(CategoryDiffCallback(field, value))
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_icon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[holder.bindingAdapterPosition]
            holder.bind(item)
        }
    }

    open class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ViewHolder(view: View) : FeatureViewHolder(view) {
        fun bind(data: DataBpi) = with(itemView) {
            val usdRate = 0.000021 // อัตราแลกเปลี่ยน USD เป็น BTC
            val eurRate = 0.000024 // อัตราแลกเปลี่ยน EUR เป็น BTC
            val gbpRate = 0.000028 // อัตราแลกเปลี่ยน GBP เป็น BTC

            var usdAmount = 0.0 // จำนวนเงินใน USD
            var eurAmount = 0.0 // จำนวนเงินใน EUR
            var gbpAmount = 0.0// จำนวนเงินใน GBP
            val pattern = "[^\\d.]".toRegex()
            val output = pattern.replace(data.rate_float.toString(), "")
            when (data.code) {
                "USD" -> usdAmount = output.toDouble()
                "EUR" -> eurAmount = output.toDouble()
                "GBP" -> gbpAmount = output.toDouble()
            }
            val btcFromUsd = usdAmount * usdRate // แปลงค่า USD เป็น BTC
            val btcFromEur = eurAmount * eurRate // แปลงค่า EUR เป็น BTC
            val btcFromGbp = gbpAmount * gbpRate

            tvName.text = data.code
            tvDescription.text = data.description
            tvRate.text = data.rate

             when (data.code) {
                "USD" -> tvRateFloat.text = "BTC = $btcFromUsd"
                "EUR" -> tvRateFloat.text = "BTC = $btcFromEur"
                "GBP" -> tvRateFloat.text = "BTC = $btcFromGbp"
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

