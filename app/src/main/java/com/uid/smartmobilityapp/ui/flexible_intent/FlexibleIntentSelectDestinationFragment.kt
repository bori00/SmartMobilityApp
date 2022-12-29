package com.uid.smartmobilityapp.ui.flexible_intent

import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
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
import com.uid.smartmobilityapp.databinding.FragmentAddLocationBinding
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectDestinationBinding
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSelectOptimizationBinding
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations
import java.io.IOException

class FlexibleIntentSelectDestinationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: FlexibleIntentViewModel
    private var _binding: FragmentFlexibleIntentSelectDestinationBinding? = null
    private lateinit var _root : View
    private lateinit var nextButton: Button

    private var _mMapView: MapView? = null
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var _mMap: GoogleMap? = null

    private lateinit var _searchView: SearchView

    private var _selectedAddressMarker: Marker? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = FlexibleIntentViewModel

        _binding = FragmentFlexibleIntentSelectDestinationBinding.inflate(inflater, container, false)
        _root = binding.root

        setup(savedInstanceState)

        return _root
    }

    fun setup(savedInstanceState : Bundle?) {
        val confirmDestinationButton: Button = binding.confirmDestinationButtonId
        confirmDestinationButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_nav_flexible_intent_select_destination_to_nav_flexible_intent_select_transport)
        }

        val summaryButton: Button = binding.include.editRouteButtonId
        summaryButton.isVisible = false

        val nextStop: TextView = binding.include.nextStopTextFieldId
        var text = "Current location"
        nextStop.text = text

        setupMap(savedInstanceState)
        setupViewModel()

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
        _searchView.onActionViewExpanded()
        _searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val location = _searchView.query.toString()
                var addressList: List<Address>? = null
                if (location.isNotEmpty()) {
                    val geocoder = Geocoder(MainActivity.context)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                        if (!addressList.isEmpty()) {
                            viewModel.selectedAddress.value = addressList[0]

                            val nextStop: TextView = binding.include.nextStopTextFieldId
                            var text: String = "Current location"
                            val addition = "➔" + viewModel.selectedAddress.value!!.getAddressLine(0)
                            text += addition
                            nextStop.text = text
                        } else {
                            Toast.makeText(MainActivity.context, "This location couldn't be found. Please make your query more specific", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(MainActivity.context, "This location couldn't be found. Please make your query more specific", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
                } else {
                    Toast.makeText(MainActivity.context, "Please select an address", Toast.LENGTH_SHORT).show()
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
        viewModel.selectedAddress.observe(viewLifecycleOwner) {
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


    private fun updateButtonState() {

    }

}