package dev.fstudio.weather.ui.fragment.greenhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dev.fstudio.weather.databinding.FragmentLoginBinding
import dev.fstudio.weather.ui.fragment.weather.WeatherFragmentDirections

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            var r= binding.editTextTextPassword.text
            if (binding.editTextTextPassword.text.toString() == "password") {
                Navigation.findNavController(binding.root)
                    .navigate(LoginFragmentDirections.actionLoginFragmentToSensorFragment())
            } else {
                Toast.makeText(context, "Wrong password or login", Toast.LENGTH_LONG).show()
            }
        }
    }

}