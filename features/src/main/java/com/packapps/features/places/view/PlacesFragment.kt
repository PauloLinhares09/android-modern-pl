package com.packapps.features.places.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.packapps.core.utils.Constants
import com.packapps.features.R
import com.packapps.features.databinding.FragmentPlacesBinding
import com.packapps.features.places.PlacesViewModel
import com.packapps.features.places.model.data.FilterData
import com.packapps.features.places.view.adapter.PlacesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlacesFragment : Fragment(), FilterDialogFragment.FilterDialogListener {

    private var _binding: FragmentPlacesBinding? = null
    private val viewModel by viewModel<PlacesViewModel>()
    private var filterDialog: FilterDialogFragment? = null
    private val filterData by lazy { FilterData(4, false, "", 100000) }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        requestLocationPermissionViaBroadcast()
        activity?.registerReceiver(locationPermissionResponseReceiver, IntentFilter(Constants.ACTION_RESPONSE_LOCATION_PERMISSION))
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(locationPermissionResponseReceiver)
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
                showFilterDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showFilterDialog() {
        if (filterDialog?.dialog?.isShowing != true) {
            filterDialog = FilterDialogFragment().also {
                it.listener = this
                it.show(childFragmentManager, "FilterDialogFragment")
            }
        }
    }

    private fun requestLocationPermissionViaBroadcast() {
        val intent = Intent(Constants.ACTION_REQUEST_LOCATION_PERMISSION)
        context?.sendBroadcast(intent)
    }

    private val locationPermissionResponseReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Constants.ACTION_RESPONSE_LOCATION_PERMISSION) {
                filterData.ll = intent.extras?.getString(Constants.LL, "").orEmpty()
                viewModel.fetchPlace(filterData)
                Toast.makeText(context, filterData.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onFilterApply(priceRange: Int, openedNow: Boolean, radius: Int) {
        filterData.priceRange = priceRange
        filterData.openedNow = openedNow
        filterData.radius = radius

        viewModel.fetchPlace(filterData)
    }

    override fun onFilterCancel() {
        filterData.priceRange = 0
        filterData.openedNow = false
        filterData.radius = 100000

        viewModel.fetchPlace(filterData)
    }
}