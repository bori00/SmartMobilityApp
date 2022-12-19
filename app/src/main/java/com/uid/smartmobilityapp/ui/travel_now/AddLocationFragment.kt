package com.uid.smartmobilityapp.ui.travel_now

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.databinding.ActivityTravelNowBinding
import java.io.IOException

class AddLocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: ActivityTravelNowBinding? = null
//    private lateinit var mMap: GoogleMap

    lateinit private var _viewModel: LocationsViewModel;

    private var _mMap: GoogleMap? = null
    private var _mMapView: MapView? = null
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

    private lateinit var _searchView: SearchView

    private var _selectedAddressMarker: Marker? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainActivity", "Open Add Location Fragment")
        _viewModel =
            ViewModelProvider(this).get(LocationsViewModel::class.java)

        _binding = ActivityTravelNowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupMap(savedInstanceState)
        setupViewModel()

//        val saveBookmarkFAB = binding.saveBookmarkFAB
//        saveBookmarkFAB.setOnClickListener { saveBookmark() }

        return root
    }

    private fun setupMap(savedInstanceState: Bundle?) {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        //objects or sub-Bundles.
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        _mMapView = binding.locationMapView
        _mMapView!!.onCreate(mapViewBundle)

        setupMapSearch()

        _mMapView!!.getMapAsync(this)
    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//
//        //Add a marker in Sydney and move the camera
//        val bucharest = LatLng(44.4, 26.09)
//        mMap.addMarker(MarkerOptions().position(bucharest).title("Marker in bucharest"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(bucharest))
//
//
//    }

    private fun setupMapSearch() {
        // inspired by https://www.geeksforgeeks.org/how-to-add-searchview-in-google-maps-in-android/
        _searchView = binding.getLocationSearchView
        _searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val location = _searchView.query.toString()
                var addressList: List<Address>? = null
                if (location.isNotEmpty()) {
                    val geocoder = Geocoder(MainActivity.context)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                        if (!addressList.isEmpty()) {
                            _viewModel.selectedAddress.value = addressList[0]
                        } else {
                            Toast.makeText(
                                MainActivity.context,
                                "This location couldn't be found. Please make your query more specific",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(
                            MainActivity.context,
                            "This location couldn't be found. Please make your query more specific",
                            Toast.LENGTH_SHORT
                        ).show()
                        e.printStackTrace()
                    }
                } else {
                    Toast.makeText(
                        MainActivity.context,
                        "Please select an address",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun setupViewModel() {
        // Address selection
        _viewModel.selectedAddress.observe(viewLifecycleOwner) {
            if (it != null && _mMap != null) {
                val latLng = LatLng(it.latitude, it.longitude)
                if (_selectedAddressMarker == null) {
                    _selectedAddressMarker = _mMap!!.addMarker(
                        MarkerOptions()
                            .position(latLng)
                    )
                } else {
                    _selectedAddressMarker?.position = latLng
                }
                _selectedAddressMarker?.hideInfoWindow()
                _selectedAddressMarker?.title = it.featureName
                _selectedAddressMarker?.snippet = it.getAddressLine(0)
                _selectedAddressMarker?.showInfoWindow()
                _mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }
    }


    override fun onMapReady(map: GoogleMap) {
        _mMap = map
    }
}