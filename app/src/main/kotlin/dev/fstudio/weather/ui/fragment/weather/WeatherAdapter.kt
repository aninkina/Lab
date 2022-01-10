package dev.fstudio.weather.ui.fragment.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.fstudio.weather.api.get.model.DataList
import dev.fstudio.weather.databinding.ItemWeatherBinding

class WeatherAdapter(private val dataSet: List<DataList>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val item: ItemWeatherBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(data: String) {
            item.tvWeatherName.text = data
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false

            )
        )
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(dataSet[position].main.temp.toString())
    }

    override fun getItemCount() = dataSet.size

}

