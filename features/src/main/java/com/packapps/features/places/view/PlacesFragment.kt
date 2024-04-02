package com.packapps.features.places.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.packapps.features.databinding.FragmentPlacesBinding
import com.packapps.features.places.PlacesViewModel
import com.packapps.features.places.view.adapter.PlacesAdapter
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

        val rvPlaces: RecyclerView = binding.rvPlaces

        // Configurar o LayoutManager
        rvPlaces.layoutManager = GridLayoutManager(context, 2) // 2 colunas

        // Observar os dados e configurar o adapter
        viewModel.placesListLiveData.observe(viewLifecycleOwner) { places ->
            rvPlaces.adapter = PlacesAdapter(places)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}