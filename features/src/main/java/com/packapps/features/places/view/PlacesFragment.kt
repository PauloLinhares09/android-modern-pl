package com.packapps.features.places.view

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.packapps.features.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvPlaces: RecyclerView = binding.rvPlaces
        rvPlaces.layoutManager = GridLayoutManager(context, 2) // 2 colunas
        viewModel.placesListLiveData.observe(viewLifecycleOwner) { places ->
            rvPlaces.adapter = PlacesAdapter(places)
        }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.places_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                Toast.makeText(context, "Filter", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}