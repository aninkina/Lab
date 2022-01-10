package dev.fstudio.weather.ui.fragment.greenhouse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dev.fstudio.weather.api.get.Sensor
import dev.fstudio.weather.databinding.FragmentSensorBinding
import dev.fstudio.weather.lat
import dev.fstudio.weather.lon
import dev.fstudio.weather.ui.fragment.alerts.AlertsAdapter
import kotlinx.coroutines.launch

class SensorFragment : Fragment() {

    private lateinit var binding: FragmentSensorBinding
    private lateinit var sensorAdapter: SensorAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSensorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.button2.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(SensorFragmentDirections.actionSensorFragmentToChartFragment())
        }
    }


    private fun setupRecyclerView(){
        sensorAdapter = SensorAdapter(sensors)
        binding.rvSensorItems.adapter = sensorAdapter
        binding.rvSensorItems.layoutManager = LinearLayoutManager(requireContext())
    }


    companion object{
        val sensors = listOf<Sensor>(
            Sensor("Название датчика", "Серийный номер", "Место датчика", true, "Kolya"),
            Sensor("Sensor#12", "00213", "Теплица №1", true, "Kolya"),
            Sensor("Sensor#10", "00210", "Теплица №2", false, "Kolya"),
            Sensor("Sensor#1", "00211", "Дом", true, "Kolya"),
            Sensor("Sensor#16", "00216", "Подвал", false, "Kolya"),
            Sensor("Sensor#3", "00212", "Веранда", true, "Kolya"),
        )
    }
}