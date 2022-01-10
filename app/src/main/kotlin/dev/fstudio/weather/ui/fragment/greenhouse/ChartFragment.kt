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
        val places = arrayOf("window", "door", "plot1", "plot2")

        val adapter = ArrayAdapter<String>(
            view.context, android.R.layout.simple_dropdown_item_1line, places
        )
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.autoCompleteTextView.threshold = 1
        binding.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, _,
                                                                                             position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
        }



        //Part1
        val entries = ArrayList<Entry>()

//Part2
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))

//Part3
        val vl = LineDataSet(entries, "My Type")

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
        binding.lineChart.xAxis.axisMaximum = 10+0.1f

//Part8
        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setPinchZoom(true)

//Part9
        binding.lineChart.description.text = "Days"
        binding.lineChart.setNoDataText("No forex yet!")

//Part10
        binding.lineChart.animateX(1800, Easing.EaseInExpo)

//Part11
//        val markerView = CustomMarker(this@ShowForexActivity, R.layout.marker_view)
//        lineChart.marker = markerView
    }


    companion object{
        val tempList = arrayOf<SensorResult>(
            SensorResult("10:23:54",27.5),
            SensorResult("10:25:54",28.5),
            SensorResult("10:27:54",28.9),
            SensorResult("10:28:54",29.1),
            SensorResult("10:30:54",29.7),
            SensorResult("10:31:54",30.5),
            SensorResult("10:23:54",31.8),
            SensorResult("10:25:54",32.5),
            SensorResult("10:27:54",32.9),
            SensorResult("10:28:54",35.5),
            SensorResult("10:30:54",36.1),
            SensorResult("10:31:54",36.8),
        )
    }
}
