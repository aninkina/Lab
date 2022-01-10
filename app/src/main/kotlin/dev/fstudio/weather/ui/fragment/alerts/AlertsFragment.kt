package dev.fstudio.weather.ui.fragment.alerts

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.fstudio.weather.AlarmReceiver
import dev.fstudio.weather.R
import dev.fstudio.weather.api.OpenWeatherApi
import dev.fstudio.weather.databinding.FragmentAlertsBinding
import dev.fstudio.weather.lat
import dev.fstudio.weather.lon
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

class AlertsFragment : Fragment(R.layout.fragment_alerts) {

    private lateinit var alertsAdapter: AlertsAdapter
    private lateinit var binding: FragmentAlertsBinding
    private val service by inject<OpenWeatherApi>()
    private val preferences: SharedPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlertsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        activity?.let { setAlarm(it) }
    }

    private fun setupRecyclerView() = binding.rvAlertsItems.apply {
        lifecycleScope.launch {
            runCatching {
                service.getFullForecast(lat, lon)
            }.onSuccess {

                if (it.alerts != null) {
                    Log.d("my_logs", it.alerts[0].event)
                    alertsAdapter = AlertsAdapter(it.alerts)
                    adapter = alertsAdapter
                }

            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

        private fun setAlarm(context: Context) {
            val intent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setRepeating(
                AlarmManager.RTC_WAKEUP,
                Calendar.getInstance().getTimeInMillis(),
                (1000 * 60 * 5).toLong(),
                pendingIntent
            )
        }
    }