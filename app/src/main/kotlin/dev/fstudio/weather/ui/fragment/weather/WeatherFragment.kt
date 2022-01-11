package dev.fstudio.weather.ui.fragment.weather

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dev.fstudio.weather.R
import dev.fstudio.weather.api.OpenWeatherApi
import dev.fstudio.weather.databinding.FragmentWeatherBinding
import dev.fstudio.weather.lat
import dev.fstudio.weather.lon
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class WeatherFragment : Fragment() {

    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var binding: FragmentWeatherBinding
    private val service by inject<OpenWeatherApi>()
    private val preferences: SharedPreferences by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.rvWeatherItems.apply {
        lifecycleScope.launch {
            runCatching {
                service.getForecast(lat, lon)
            }.onSuccess {
                weatherAdapter = WeatherAdapter(it.list)
                adapter = weatherAdapter
                binding.tvName.text = it.city.name
                binding.tvCountry.text = it.city.country

            }
        }
        layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSettings -> {
                Navigation.findNavController(binding.root)
                    .navigate(WeatherFragmentDirections.actionWeatherFragmentToSettingsFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}