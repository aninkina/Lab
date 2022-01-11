package dev.fstudio.weather.ui.fragment.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.fstudio.weather.api.get.model.DataList
import dev.fstudio.weather.api.get.model.SimpleWeather
import dev.fstudio.weather.databinding.ItemWeatherBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(private val dataSet: List<DataList>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val item: ItemWeatherBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(dateMilliseconds: String, temp: Double, feels_like: Double) {


            item.tvDate.text = dateMilliseconds

            item.tvTemp.text = temp.toString()
            item.tvTemp.text = feels_like.toString()

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
        val el = dataSet[position]


        if(position < listweather.size)
        weatherViewHolder.bind(listweather[position].date, listweather[position].temp,
            listweather[position].feelsLike)

    }

    override fun getItemCount() = dataSet.size

    companion object{
        val listweather = arrayOf<SimpleWeather>(
            SimpleWeather("13:00", -13.0, -13.5),
            SimpleWeather("14:00", -13.0, -13.7),
            SimpleWeather("15:00", -13.9, -13.7),
            SimpleWeather("16:00", -13.0, -13.7),
            SimpleWeather("17:00", -13.6, -13.6),
            SimpleWeather("18:00", -14.0, -14.5),
            SimpleWeather("19:00", -14.0, -14.5),
            SimpleWeather("20:00", -15.0, -15.5),
        )
    }

}

