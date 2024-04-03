package com.packapps.features.place.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.packapps.core.utils.Constants
import com.packapps.features.databinding.FragmentPlaceDetailBinding
import com.packapps.features.place.view.adapter.PlaceDetailsAdapter
import com.packapps.features.places.model.data.PlaceViewData

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PlaceDetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedPlace = arguments?.getParcelable<PlaceViewData>(Constants.PLACE)
        val placesArray = arguments?.getParcelableArray(Constants.PLACES_LIST)

        val placesList = placesArray?.let { array ->
            // Converte o array para um ArrayList de PlaceViewData
            ArrayList(array.map { it as PlaceViewData })
        }


        val rvPlaces: RecyclerView = binding.recyclerView
        rvPlaces.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        selectedPlace?.let {
            placesList?.add(0, it)
            adapter = PlaceDetailsAdapter(placesList?: mutableListOf(), it)
            rvPlaces.adapter = adapter

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(rvPlaces)
        }


    }
}
