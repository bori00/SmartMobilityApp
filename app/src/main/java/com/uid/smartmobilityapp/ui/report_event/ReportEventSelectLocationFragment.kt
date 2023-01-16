package com.uid.smartmobilityapp.ui.report_event

import android.annotation.SuppressLint
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.provider.BaseColumns
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
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentReportEventBinding
import com.uid.smartmobilityapp.databinding.FragmentReportEventSuccessBinding
import com.uid.smartmobilityapp.databinding.FragmentSelectLocationBinding
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.services.DeviceLocationProviderService
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.bookmarks.model.MyBookmarks
import com.uid.smartmobilityapp.ui.utils.MapSearchUtils
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors


class ReportEventSelectLocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentSelectLocationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel : ReportEventViewModel

    private var _mMapView: MapView? = null
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var _mMap: GoogleMap? = null

    private var _selectedAddressMarker : Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ReportEventViewModel

        _binding = FragmentSelectLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.discardLocationChangesButton.setOnClickListener{onDiscardLocationChanges()}
        binding.saveLocationChangesButton.setOnClickListener{onSaveLocationChanges()}
        binding.setLocationToCurrentLocationButton.setOnClickListener{onSetLocationToCurrentLocation()}
        setupMap(savedInstanceState)

        viewModel.newlySelectedLocation.value = viewModel.selectedLocation.value

        return root
    }

    private fun onDiscardLocationChanges() {
        viewModel.newlySelectedLocation.value = viewModel.selectedLocation.value
        binding.root.findNavController().popBackStack()
//        binding.root.findNavController().navigate(R.id.action_nav_report_event_select_location_to_nav_report_event)
    }

    private fun onSaveLocationChanges() {
        viewModel.selectedLocation.value = viewModel.newlySelectedLocation.value;
        binding.root.findNavController().popBackStack()
//        binding.root.findNavController().navigate(R.id.action_nav_report_event_select_location_to_nav_report_event)
    }

    private fun onSetLocationToCurrentLocation() {
        viewModel.newlySelectedLocation.value = AddressWithName(DeviceLocationProviderService().getCurrentLocation(), "Current Location")
    }

    override fun onMapReady(map: GoogleMap) {
        _mMap = map
        setupViewModel()
        setupMapSearch()
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

        _mMapView!!.getMapAsync(this)
    }

    private fun setupMapSearch() {

        MapSearchUtils().setupMapSearchWithBookmarkSuggestions(binding.locationSearchView, requireContext(), requireView(),
            {addressWithName : AddressWithName -> viewModel.newlySelectedLocation.value = addressWithName},
            {bookmark : Bookmark -> viewModel.newlySelectedLocation.value = AddressWithName(bookmark.address, bookmark.name)}
        )
    }

    private fun setupViewModel() {
        // Address selection
        viewModel.newlySelectedLocation.observe(viewLifecycleOwner) {
            Log.d("ReportEventSelectLocationFragment","Updating Newly selected address marker: " + it)
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