package com.packapps.features.place.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.packapps.features.databinding.FragmentPlaceDetailBinding
import com.packapps.features.place.model.PlaceDetailViewData
import com.packapps.features.place.view.adapter.PlaceDetailsAdapter
import com.packapps.features.places.model.data.PlaceViewData

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlaceDetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedPlace = arguments?.getParcelable<PlaceViewData>("selected_place")
        val placesList = arguments?.getParcelableArrayList<PlaceViewData>("places_list")


        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = PlaceDetailsAdapter(getPlaceDetails())
        recyclerView.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    private fun getPlaceDetails(): List<PlaceDetailViewData> {
        // Implemente a lógica para obter os detalhes do lugar
        return emptyList()
    }
}
