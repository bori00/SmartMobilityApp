package com.uid.smartmobilityapp.ui.travel_now

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.VehicleListActivity
import com.uid.smartmobilityapp.databinding.FragmentAddLocationBinding
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations.locations
import java.io.IOException

class AddLocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentAddLocationBinding? = null
    lateinit private var _viewModel: LocationsViewModel;

    private var _mMapView: MapView? = null
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var _mMap: GoogleMap? = null

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

        _binding = FragmentAddLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val travelNowButton: Button = binding.searchRoutesButtonId
        travelNowButton.setOnClickListener {
            val intent = Intent(MainActivity.context, VehicleListActivity::class.java)
            startActivity(intent)
//            binding.root.findNavController().navigate(R.id.action_nav_home_to_travel_now)

        }

        val summaryButton: Button = binding.include.editRouteButtonId
        summaryButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_travel_now_to_locations)
        }

        val nextStop: TextView = binding.include.nextStopTextFieldId
        var text: String = "Current location"
        for (loc: Location in locations) {
            if (loc.indexNo.toInt() > 1) {
                val addition = "➔${loc.name}"
                text += addition
            }
        }
        nextStop.text = text

        setupMap(savedInstanceState)
        setupViewModel()

        return root
    }

    override fun onMapReady(map: GoogleMap) {
        _mMap = map
    }

    override fun onResume() {
        _mMapView?.onResume()
        super.onResume()
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

    private fun setupMapSearch() {
        // inspired by https://www.geeksforgeeks.org/how-to-add-searchview-in-google-maps-in-android/
        _searchView = binding.getLocationSearchView
        _searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val bundle: Bundle? = arguments
                var i=-1
                if (bundle != null) {
                    i = bundle.getInt("position", -1)
                }
                Log.d("BUNDLE",i.toString())
                val location = _searchView.query.toString()
                var addressList: List<Address>? = null
                if (location.isNotEmpty()) {
                    val geocoder = Geocoder(MainActivity.context)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                        if (!addressList.isEmpty()) {
                            _viewModel.selectedAddress.value = addressList[0]
                            if(i!=-1){
                                _viewModel.locations.value?.set(i, Location(
                                    query,
                                    (_viewModel.locations.value!!.lastIndex + 2).toString()
                                ))

                            }else{
                                _viewModel.locations.value?.add(
                                    Location(
                                        query,
                                        (_viewModel.locations.value!!.lastIndex + 2).toString()
                                    )
                                )
                            }
                            val nextStop: TextView = binding.include.nextStopTextFieldId
                            var text: String = "Current location"
                            for (loc: Location in locations) {
                                if (loc.indexNo.toInt() > 1) {
                                    val addition = "➔${loc.name}"
                                    text += addition
                                }
                            }
                            nextStop.text = text
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

}