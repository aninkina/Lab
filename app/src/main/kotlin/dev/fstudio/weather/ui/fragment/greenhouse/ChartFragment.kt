package dev.fstudio.weather.ui.fragment.greenhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.fstudio.weather.databinding.FragmentChartBinding
import android.widget.AdapterView

import android.widget.ArrayAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dev.fstudio.weather.R
import dev.fstudio.weather.api.get.SensorResult
import kotlin.random.Random


class ChartFragment : Fragment() {

    private lateinit var binding: FragmentChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val places = arrayOf(" Теплица №1", " Теплица №2", " Дом", " Подвал", " Веранда")

        val adapter = ArrayAdapter<String>(
            view.context, android.R.layout.simple_dropdown_item_1line, places
        )
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.autoCompleteTextView.threshold = 1
        binding.autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _,
                                              position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                getArrayEntries(position)
            }

    }


    private fun getArrayEntries(pos: Int) {
        val entries = ArrayList<Entry>()

        if (pos == 0) {
            //Part1
            for (i in 1..10) {
                val x = (14.0f + (i * 2) / 100.0).toFloat()
                val y = Random.nextDouble(18.0, 40.0).toFloat()
                entries.add(Entry(x, y))
            }
        } else if (pos == 1) {

        } else if (pos == 2) {
            //Part1
            for (i in 1..10) {
                val x = (14.0f + (i * 2) / 100.0).toFloat()
                val y = Random.nextDouble(-30.0, 30.0).toFloat()
                entries.add(Entry(x, y))
            }
        } else if (pos == 3) {

        } else if (pos == 4) {
            //Part1
            for (i in 1..10) {
                val x = (14.0f + (i * 2) / 100.0).toFloat()
                val y = Random.nextDouble(0.0, 35.0).toFloat()
                entries.add(Entry(x, y))
            }
        } else {
            throw Exception("Exception threw in getArrayEntries")
        }

        setChart(entries)
    }

    private fun setChart(entries: ArrayList<Entry>) {

//Part3
        val vl = LineDataSet(entries, "Temperature")

//Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.grey
        vl.fillAlpha = R.color.red

//Part5
        binding.lineChart.xAxis.labelRotationAngle = 0f

//Part6
        binding.lineChart.data = LineData(vl)

//Part7
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.xAxis.axisMaximum = 14.05f + 0.1f

//Part8
        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setPinchZoom(true)

//Part9
        binding.lineChart.description.text = "Time"
        binding.lineChart.setNoDataText("No forex yet!")

//Part10
        binding.lineChart.animateX(1800, Easing.EaseInExpo)
    }
}
