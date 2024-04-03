package com.packapps.android_modern_pl

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.packapps.android_modern_pl.databinding.ActivityMainBinding
import com.packapps.core.utils.Constants
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.packapps.features.places.model.data.PlaceViewData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_places, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter().apply {
            addAction(Constants.ACTION_REQUEST_LOCATION_PERMISSION)
            addAction(Constants.ACTION_PLACE_CLICK)
        }
        registerReceiver(locationPermissionRequestReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(locationPermissionRequestReceiver)
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // A permissão não foi concedida, solicite ao usuário
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            // A permissão já foi concedida, você pode prosseguir com a obtenção da localização
            getLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            } else {
                // A permissão foi negada. Você pode tratar isso conforme necessário.
                Log.d("Location", "Permission denied")
            }
        }
    }

    @SuppressLint("MissingPermission") // Assegure que a permissão foi checada antes de chamar esse método
    private fun getLocation() {
        val task: Task<Location> = fusedLocationClient.lastLocation
        task.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude

                responseLocationViaBroadcast(longitude, latitude)
            }
        }.addOnFailureListener {
            Log.e("Location", "Error getting location")
        }
    }

    //#### Broadcast location
    private val locationPermissionRequestReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            when(intent?.action) {
                Constants.ACTION_REQUEST_LOCATION_PERMISSION -> {
                    checkLocationPermission()
                }
                Constants.ACTION_PLACE_CLICK -> {
                    openFragmentInNavHostFragment(intent)
                }

            }
        }
    }

    private fun responseLocationViaBroadcast(longitude: Double, latitude: Double) {
        val intent = Intent(Constants.ACTION_RESPONSE_LOCATION_PERMISSION)
        intent.putExtra(Constants.LL, "$latitude,$longitude")
        sendBroadcast(intent)
    }

    private fun openFragmentInNavHostFragment(intent: Intent) {

        val place: PlaceViewData? = intent.getParcelableExtra(Constants.PLACE)
        val placesList: ArrayList<PlaceViewData>? = intent.getParcelableArrayListExtra(Constants.PLACES_LIST)

        place?.let {
            placesList?.let {
                val navController = findNavController(R.id.nav_host_fragment_activity_main)
                val action = MobileNavigationDirections.actionGlobalPlaceDetailFragment(place, placesList.toTypedArray())
                navController.navigate(action)
            }
        }



    }






    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}