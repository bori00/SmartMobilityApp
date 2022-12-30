package com.uid.smartmobilityapp.ui.report_event

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
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
import com.uid.smartmobilityapp.databinding.FragmentReportEventBinding
import com.uid.smartmobilityapp.databinding.FragmentReportEventSuccessBinding
import com.uid.smartmobilityapp.databinding.FragmentSelectLocationBinding
import com.uid.smartmobilityapp.models.AddressWithName
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer


class ReportEventSelectLocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentSelectLocationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel : ReportEventViewModel

    private var _mMapView: MapView? = null
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var _mMap: GoogleMap? = null
    private lateinit var _searchView: SearchView

    private var _selectedAddressMarker : Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ReportEventViewModel
        viewModel.newlySelectedLocation.value = viewModel.selectedLocation.value

        _binding = FragmentSelectLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.discardLocationChangesButton.setOnClickListener{onDiscardLocationChanges()}
        binding.saveLocationChangesButton.setOnClickListener{onSaveLocationChanges()}
        setupMap(savedInstanceState)
        setupViewModel()

        return root
    }

    private fun onDiscardLocationChanges() {
        viewModel.newlySelectedLocation.value = viewModel.selectedLocation.value
        binding.root.findNavController().navigate(R.id.action_nav_report_event_select_location_to_nav_report_event)
    }

    private fun onSaveLocationChanges() {
        viewModel.selectedLocation.value = viewModel.newlySelectedLocation.value;
        binding.root.findNavController().navigate(R.id.action_nav_report_event_select_location_to_nav_report_event)
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
        // objects or sub-Bundles.
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
        _searchView = binding.locationSearchView
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
                            viewModel.newlySelectedLocation.value = AddressWithName(addressList[0], addressList[0].getAddressLine(0)) // TODO: bookmark name...
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
        viewModel.newlySelectedLocation.observe(viewLifecycleOwner) {
            if (it != null && _mMap != null) {
                val latLng = LatLng(it.address.latitude, it.address.longitude)
                if (_selectedAddressMarker == null) {
                    _selectedAddressMarker = _mMap!!.addMarker(
                        MarkerOptions()
                            .position(latLng)
                    )
                } else {
                    _selectedAddressMarker?.position = latLng
                }
                _selectedAddressMarker?.hideInfoWindow()
                _selectedAddressMarker?.title = it.name
                _selectedAddressMarker?.snippet = it.address.getAddressLine(0)
                _selectedAddressMarker?.showInfoWindow()
                _mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }
    }
}