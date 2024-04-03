package com.packapps.features.place.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.packapps.core.navigation.NavigationCommand
import com.packapps.core.utils.Constants
import com.packapps.features.databinding.FragmentPlaceDetailBinding
import com.packapps.features.place.view.adapter.PlaceDetailsAdapter
import com.packapps.features.places.model.data.PlaceViewData

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val selectedPlace = arguments?.getParcelable<PlaceViewData>(Constants.PLACE)
        binding.tvPlaceName.text = selectedPlace?.venueName

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                (activity as? NavigationCommand)?.navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
