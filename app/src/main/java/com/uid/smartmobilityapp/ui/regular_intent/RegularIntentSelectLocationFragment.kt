package com.uid.smartmobilityapp.ui.regular_intent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSetupBinding
import com.uid.smartmobilityapp.databinding.FragmentRegularIntentSelectLocationBinding
import com.uid.smartmobilityapp.databinding.FragmentRegularIntentSetupBinding
import com.uid.smartmobilityapp.databinding.FragmentSelectLocationBinding
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.services.DeviceLocationProviderService
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntentLocation
import com.uid.smartmobilityapp.ui.travel_now.LocationsViewModel
import com.uid.smartmobilityapp.ui.utils.MapSearchUtils

class RegularIntentSelectLocationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: RegularIntentViewModel
    private var _binding: FragmentSelectLocationBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var _mMapView: MapView? = null
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var _mMap: GoogleMap? = null

    private var _selectedAddressMarker : Marker? = null

    private var isEditForStartingPoint : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = RegularIntentViewModel

        _binding = FragmentSelectLocationBinding.inflate(inflater, container, false)
        _root = binding.root

        binding.discardLocationChangesButton.setOnClickListener{onDiscardLocationChanges()}
        binding.saveLocationChangesButton.setOnClickListener{onSaveLocationChanges()}
        binding.setLocationToCurrentLocationButton.setOnClickListener{onSetLocationToCurrentLocation()}
        setupMap(savedInstanceState)

        val bundle: Bundle? = arguments
        var i=-1
        if (bundle != null) {
            i = bundle.getInt("position", -1)
        }

        isEditForStartingPoint = i == 0

        if(isEditForStartingPoint) {
            viewModel.previousLocation.value = viewModel.startingPoint.value?.name?.let {
                viewModel.startingPoint.value!!.address?.let { it1 ->
                    AddressWithName(
                        it1,
                        it
                    )
                }
            }
            binding.setLocationToCurrentLocationButton.isVisible = true
        } else {
            viewModel.previousLocation.value = viewModel.destination.value?.name?.let {
                viewModel.startingPoint.value!!.address?.let { it1 ->
                    AddressWithName(
                        it1,
                        it
                    )
                }
            }
            binding.setLocationToCurrentLocationButton.isVisible = false
        }

        return _root
    }

    private fun onDiscardLocationChanges() {
        if(isEditForStartingPoint) {
            viewModel.startingPointNewAddress.value = viewModel.previousLocation.value
        } else {
            viewModel.destinationNewAddress.value = viewModel.previousLocation.value
        }
        binding.root.findNavController().navigate(R.id.action_nav_regular_intent_select_location_nav_regular_intent_setup)
    }

    private fun onSaveLocationChanges() {
        binding.root.findNavController().navigate(R.id.action_nav_regular_intent_select_location_nav_regular_intent_setup)
    }

    private fun onSetLocationToCurrentLocation() {
        if(isEditForStartingPoint) {
            viewModel.startingPointNewAddress.value = AddressWithName(DeviceLocationProviderService().getCurrentLocation(), "Current Location")
        }
    }

    override fun onMapReady(map: GoogleMap) {
        _mMap = map

        if(isEditForStartingPoint) {
            setupViewModel(viewModel.startingPointNewAddress)
        } else {
            setupViewModel(viewModel.destinationNewAddress)
        }

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

        if(isEditForStartingPoint) {
            MapSearchUtils().setupMapSearchWithBookmarkSuggestions(binding.locationSearchView, requireContext(), requireView(),
                {addressWithName : AddressWithName -> viewModel.startingPointNewAddress.value = addressWithName},
                {bookmark : Bookmark -> viewModel.startingPointNewAddress.value = AddressWithName(bookmark.address, bookmark.name) }
            )
        } else {
            MapSearchUtils().setupMapSearchWithBookmarkSuggestions(binding.locationSearchView, requireContext(), requireView(),
                {addressWithName : AddressWithName -> viewModel.destinationNewAddress.value = addressWithName},
                {bookmark : Bookmark -> viewModel.destinationNewAddress.value = AddressWithName(bookmark.address, bookmark.name) }
            )
        }

    }

    private fun setupViewModel(newLocation: MutableLiveData<AddressWithName?>) {
        // Address selection
        newLocation.observe(viewLifecycleOwner) {
            Log.d("RegularIntentSelectLocationFragment","Updating Newly selected address marker: " + it)
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