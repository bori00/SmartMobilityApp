package com.uid.smartmobilityapp.ui.company.input_bikes

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.CompanyActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.databinding.FragmentAddInputBikesBinding
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.ui.company.input_bikes.model.InputBike
import com.uid.smartmobilityapp.ui.company.input_bikes.model.MyInputBikes
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations
import com.uid.smartmobilityapp.ui.utils.MapSearchUtils
import java.io.IOException

class AddInputBikeLocation : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentAddInputBikesBinding? = null
    lateinit private var _viewModel: InputBikeLocationViewModel;

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
        Log.d("CompanyActivity", "Open Add Location Fragment")
        _viewModel =
            ViewModelProvider(this).get(InputBikeLocationViewModel::class.java)

        _binding = FragmentAddInputBikesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val nrBikes: TextView = binding.noBikesTF

        val okButton: FloatingActionButton = binding.floatingActionButton2
        okButton.setOnClickListener {
            val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
            if (nrBikes.text.toString() != "" && nrBikes.text.toString().matches(regex)) {
                var addressExists = false
                if(_viewModel.query.value!=null){
                    for (loc: InputBike in MyInputBikes.input_bikes) {
                        Log.d("ADDR",loc.address.toString())
                        Log.d("COMP",_viewModel.selectedAddress.value!!.toString())
                        if (loc.address.toString() ==_viewModel.selectedAddress.value!!.toString()) {
                            addressExists = true
                        }
                        Log.d("BUG",addressExists.toString())
                    }
                    if(!addressExists){
                        _viewModel.input_bikes.value?.add(
                            InputBike(
                                _viewModel.query.value!!,
                                nrBikes.text.toString(),
                                _viewModel.selectedAddress.value!!
                            )
                        )
                        binding.root.findNavController()
                            .navigate(R.id.action_add_input_bike_location_to_input_bike_locations)
                    } else {
                        Toast.makeText(CompanyActivity.context, "Address already exists", Toast.LENGTH_SHORT).show()
                    }
               }
                else {
                    Toast.makeText(CompanyActivity.context, "Please introduce an address", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(CompanyActivity.context, "Please introduce a number of available bikes", Toast.LENGTH_SHORT).show()
            }

        }

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
        _mMapView = binding.mapView
        _mMapView!!.onCreate(mapViewBundle)

        setupMapSearch()

        _mMapView!!.getMapAsync(this)
    }

    private fun setupMapSearch() {
        MapSearchUtils().setupMapSearchWithNoBookmarkSuggestions(binding.getLocationSearchView,
            {addressWithName : AddressWithName ->
                _viewModel.selectedAddress.value = addressWithName.address
                _viewModel.query.value = addressWithName.name
            },
            CompanyActivity.context
        )
        // inspired by https://www.geeksforgeeks.org/how-to-add-searchview-in-google-maps-in-android/
//        _searchView = binding.getLocationSearchView
//
//        _searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                val location = _searchView.query.toString()
//                var addressList: List<Address>? = null
//                if (location.isNotEmpty()) {
//                    val geocoder = Geocoder(CompanyActivity.context)
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 1)
//                        if (!addressList.isEmpty()) {
//                            _viewModel.selectedAddress.value = addressList[0]
//                            _viewModel.query.value = query
////                            _viewModel.input_bikes.value?.add(
////                                InputBike(
////                                    query,
////                                    "",
////                                    _viewModel.selectedAddress.value!!
////                                )
////                            )
//                        } else {
//                            Toast.makeText(
//                                CompanyActivity.context,
//                                "This location couldn't be found. Please make your query more specific",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    } catch (e: IOException) {
//                        Toast.makeText(
//                            CompanyActivity.context,
//                            "This location couldn't be found. Please make your query more specific",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        e.printStackTrace()
//                    }
//                } else {
//                    Toast.makeText(
//                        CompanyActivity.context,
//                        "Please select an address",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })
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