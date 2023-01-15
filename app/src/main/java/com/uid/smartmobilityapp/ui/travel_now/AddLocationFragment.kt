package com.uid.smartmobilityapp.ui.travel_now

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.databinding.FragmentAddLocationBinding
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.company.input_bikes.model.InputBike
import com.uid.smartmobilityapp.ui.company.input_bikes.model.MyInputBikes
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyGroupSize.size
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations.locations
import com.uid.smartmobilityapp.ui.travel_now.model.MyVehicles.vehicles
import com.uid.smartmobilityapp.ui.travel_now.model.VehicleListItem
import com.uid.smartmobilityapp.ui.utils.MapSearchUtils
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
            LocationsViewModel

        _binding = FragmentAddLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _viewModel.newlySelectedLocation.value = _viewModel.selectedLocation.value

        val searchRoutesButton: Button = binding.searchRoutesButtonId
        searchRoutesButton.isEnabled = _viewModel.locations.value!!.size > 1
        if(_viewModel.selectedIntent.value === "Flexible Intent") {
            searchRoutesButton.text = "Next"
            searchRoutesButton.setOnClickListener {
                binding.root.findNavController().navigate(R.id.action_travel_now_to_flexible_intent_select_transport)
            }
        } else {
            searchRoutesButton.setOnClickListener {
                binding.root.findNavController().navigate(R.id.action_travel_now_to_vehicle_list)
            }
        }

        val summaryButton: Button = binding.include.editRouteButtonId
        summaryButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_travel_now_to_locations)
        }

        editYourRoute()

        setupMap(savedInstanceState)
        setupEditText()
        setupGroupTravelButton()
        saveStopButtonId()

        return root
    }

    private fun saveStopButtonId() {
        val saveStopButton: Button = binding.saveStopButtonId
        saveStopButton.setOnClickListener {
            if (_viewModel.newlySelectedLocation.value == null) {
                Toast.makeText(UserActivity.context, "Please select an Address", Toast.LENGTH_SHORT).show()
            } else{
                val newLocation = Location(
                    _viewModel.newlySelectedLocation.value!!.name,
                    (_viewModel.locations.value!!.lastIndex + 2).toString(),
                    _viewModel.newlySelectedLocation.value!!.address
                )
                val bundle: Bundle? = arguments
                var i = -1
                if (bundle != null) {
                    i = bundle.getInt("position", -1)
                }

                if (i != -1) {
                      newLocation.indexNo = (i+1).toString()
                    _viewModel.locations.value?.set(
                        i, newLocation)
                } else {
                    if(_viewModel.selectedIntent.value === "Flexible Intent" &&
                        _viewModel.locations.value!!.size > 1) {
                        _viewModel.locations.value?.set(
                            1, newLocation)
                    }
                    else {
                        _viewModel.locations.value?.add(newLocation)
                    }

                }
                val searchRoutesButton: Button = binding.searchRoutesButtonId
                searchRoutesButton.isEnabled = _viewModel.locations.value!!.size > 1
                editYourRoute()
            }
        }
    }

    fun editYourRoute(){
        val nextStop: TextView = binding.include.nextStopTextFieldId
        var text: String = locations[0].name
        for (loc: Location in locations) {
            if (loc.indexNo.toInt() > 1) {
                val addition = "➔${loc.name}"
                text += addition
            }
        }
        nextStop.text = text
    }

    private fun setupEditText() {
        val editText: EditText = binding.editTextID
        editText.visibility = View.GONE
    }

    private fun setupGroupTravelButton() {
        val editText: EditText = binding.editTextID
        val groupButton: Button = binding.groupButtonID
        groupButton.text = size
        groupButton.setOnClickListener {
            val number = editText.text.toString().toIntOrNull()
            if (editText.visibility == View.GONE) {
                editText.visibility = View.VISIBLE
            } else {
                if ((number != null && number > 0) || editText.text.isEmpty() )
                    editText.visibility = View.GONE
            }
            size = "1"
            vehicles = arrayListOf(
                VehicleListItem(
                    "Bus",
                    "",
                    R.drawable.bus,
                    "9:56",
                    false,
                    false,
                    false
                ),
                VehicleListItem(
                    "Walk",
                    "",
                    R.drawable.walk,
                    "9:50",
                    true,
                    true,
                    false
                ),
            )

            if (editText.text.isNotEmpty()) {
                if (number == null || number <= 0) {
                    editText.error = "Please enter a valid number"
                } else {
                    editText.error = null
                    size = editText.text.toString()
                    groupButton.text = editText.text

                    if (groupButton.text.toString() == "1") {
                        vehicles.add(
                            VehicleListItem(
                                "Bike",
                                "",
                                R.drawable.bike,
                                "9:51",
                                true,
                                true,
                                false
                            )
                        )
                    }
                }
            } else {
                groupButton.text = size
                vehicles.add(
                    VehicleListItem(
                        "Bike",
                        "",
                        R.drawable.bike,
                        "9:51",
                        true,
                        true,
                        false
                    )
                )
            }
        }
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
        //objects or sub-Bundles.
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        _mMapView = binding.locationMapView
        _mMapView!!.onCreate(mapViewBundle)

        _mMapView!!.getMapAsync(this)
    }

    private fun setupMapSearch() {
        MapSearchUtils().setupMapSearchWithBookmarkSuggestions(binding.getLocationSearchView, requireContext(), requireView(),
            {addressWithName : AddressWithName -> _viewModel.newlySelectedLocation.value = addressWithName},
            {bookmark : Bookmark -> _viewModel.newlySelectedLocation.value = AddressWithName(bookmark.address, bookmark.name) }
        )
    }

    private fun setupViewModel() {
        // Address selection
        _viewModel.newlySelectedLocation.observe(viewLifecycleOwner) {
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