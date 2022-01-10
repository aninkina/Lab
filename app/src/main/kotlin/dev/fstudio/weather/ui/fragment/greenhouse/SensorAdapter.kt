package dev.fstudio.weather.ui.fragment.greenhouse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.fstudio.weather.api.get.Sensor
import dev.fstudio.weather.databinding.ItemSensorBinding

class SensorAdapter(private val dataSet: List<Sensor>) :
    RecyclerView.Adapter<SensorAdapter.SensorViewHolder>()
{
    class SensorViewHolder(private val item: ItemSensorBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(name: String, number:String, place: String, status: Boolean) {
            item.tvName.text = name
            item.tvNumber.text = number
            item.tvPlace.text = place
            item.cbStatus.isChecked = status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        return SensorViewHolder(
            ItemSensorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        )
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        holder.bind(dataSet[position].name, dataSet[position].serial_number, dataSet[position].place, dataSet[position].status)
    }

    override fun getItemCount() = dataSet.size

}