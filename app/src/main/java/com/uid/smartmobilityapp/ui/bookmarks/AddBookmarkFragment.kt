package com.uid.smartmobilityapp.ui.bookmarks

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.uid.smartmobilityapp.databinding.FragmentAddBookmarkBinding


class AddBookmarkFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentAddBookmarkBinding ? = null
    lateinit private var _viewModel: AddBookmarkViewModel;

    private var mMapView: MapView? = null
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private lateinit var mMap: GoogleMap

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainActivity", "Open Add Bookmark Fragment")
        _viewModel =
            ViewModelProvider(this).get(AddBookmarkViewModel::class.java)

        _binding = FragmentAddBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView = binding.bookmarkMapView
        mMapView!!.onCreate(mapViewBundle)
        mMapView!!.getMapAsync(this)

        return root
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onResume() {
        mMapView?.onResume()
        super.onResume()
    }

}