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
import com.packapps.features.place.model.data.PlaceDetailState
import com.packapps.features.place.view.adapter.PlaceDetailsAdapter
import com.packapps.features.places.PlaceDetailViewModel
import com.packapps.features.places.PlacesViewModel
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.data.PlacesState
import com.packapps.features.places.view.adapter.PlacesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<PlaceDetailViewModel>()

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
        viewModel.fetchPlaceDetail(selectedPlace?.fsqId.orEmpty())
        binding.tvPlaceName.text = selectedPlace?.venueName

        viewModel.placeDetailStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PlaceDetailState.Loading -> {
                    binding.viewFlipper.displayedChild = 0
                }
                is PlaceDetailState.Success -> {
                    binding.viewFlipper.displayedChild = 1
                }
                is PlaceDetailState.Failure -> {
                    binding.viewFlipper.displayedChild = 2
                    binding.errorComponent.setErrorType(state.error)
                    binding.errorComponent.setOnTryAgainClickListener {
                        viewModel.fetchPlaceDetail(selectedPlace?.fsqId.orEmpty())
                    }
                }
            }
        }

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
