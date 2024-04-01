package com.packapps.features.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.packapps.features.databinding.FragmentPlacesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlacesFragment : Fragment() {

    private var _binding: FragmentPlacesBinding? = null
    private val viewModel by viewModel<PlacesViewModel>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPlaces

        viewModel.nameLiveData.observe(viewLifecycleOwner) { nameFromApi ->
            textView.text = "Hello World: $nameFromApi"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}