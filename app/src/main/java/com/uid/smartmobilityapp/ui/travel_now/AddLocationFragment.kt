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
import com.uid.smartmobilityapp.databinding.FragmentAddLocationBinding
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import com.uid.smartmobilityapp.ui.travel_now.model.MyGroupSize.size
import com.uid.smartmobilityapp.ui.travel_now.model.MyLocations.locations
import com.uid.smartmobilityapp.ui.travel_now.model.MyVehicles.vehicles
import com.uid.smartmobilityapp.ui.travel_now.model.VehicleListItem
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

        val searchRoutesButton: Button = binding.searchRoutesButtonId
        searchRoutesButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_travel_now_to_vehicle_list)

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
        setupEditText()
        setupGroupTravelButton()
        return root
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
                var i = -1
                if (bundle != null) {
                    i = bundle.getInt("position", -1)
                }
                val location = _searchView.query.toString()
                var addressList: List<Address>? = null
                if (location.isNotEmpty()) {
                    val geocoder = Geocoder(MainActivity.context)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                        if (!addressList.isEmpty()) {
                            _viewModel.selectedAddress.value = addressList[0]
                            if (i != -1) {
                                _viewModel.locations.value?.set(
                                    i, Location(
                                        query,
                                        (_viewModel.locations.value!!.lastIndex + 2).toString(),
                                        _viewModel.selectedAddress.value!!
                                    )
                                )

                            } else {
                                _viewModel.locations.value?.add(
                                    Location(
                                        query,
                                        (_viewModel.locations.value!!.lastIndex + 2).toString(),
                                        _viewModel.selectedAddress.value!!
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