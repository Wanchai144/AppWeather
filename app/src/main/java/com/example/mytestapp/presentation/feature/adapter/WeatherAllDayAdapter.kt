package com.example.mytestapp.presentation.feature.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.AllDayWeather
import com.example.mytestapp.data.model.CustomHourly
import com.example.mytestapp.utils.Utils
import kotlinx.android.synthetic.main.item_icon.view.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class WeatherAllDayAdapter() :
    RecyclerView.Adapter<WeatherAllDayAdapter.FeatureViewHolder>() {

    var data: List<CustomHourly> = arrayListOf()
        set(value) {
            val result = DiffUtil.calculateDiff(CategoryDiffCallback(field, value))
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_icon, parent, false)
        return IconViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        if (holder is IconViewHolder) {
            val item = data[holder.bindingAdapterPosition] as CustomHourly
            holder.bind(item)
        }
    }

    open class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class IconViewHolder(view: View) : FeatureViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(data: CustomHourly) = with(itemView) {
            val df = DecimalFormat("#.##")
            val temp = data.temp_hourly!! - 273.15
            tvWeather.text = "${df.format(temp)} °C"
            val updatedAtText =  SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(data.dt_hourly!!.toLong()*1000))
            tvTime.text = updatedAtText
            tvHumidity.text ="H: ${data.humidity_hourly} %"
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
            return if (oldObject is AllDayWeather && newObject is AllDayWeather) {
                oldObject.dt == newObject.dt &&
                        oldObject.temp == newObject.temp &&
                        oldObject.country == newObject.country
            } else {
                o == n
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            o[oldItemPosition] == n[newItemPosition]
    }


}

