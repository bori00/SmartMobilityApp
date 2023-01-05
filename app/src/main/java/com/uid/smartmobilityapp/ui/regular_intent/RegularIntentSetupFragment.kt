package com.uid.smartmobilityapp.ui.regular_intent

import android.app.TimePickerDialog
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentRegularIntentSetupBinding
import com.uid.smartmobilityapp.ui.regular_intent.model.RegularIntentLocation
import com.uid.smartmobilityapp.ui.regular_intent.adapter.LocationAdapter
import com.uid.smartmobilityapp.ui.travel_now.model.Location
import java.util.*
import kotlin.collections.ArrayList

class RegularIntentSetupFragment : Fragment() {

    private lateinit var viewModel: RegularIntentViewModel
    private lateinit var editTextIntentName : EditText
    private lateinit var dropdown : AutoCompleteTextView
    private lateinit var arrivalTimeButton: Button
    private lateinit var continueButton: Button

    private var hour : Int = 0
    private var minute : Int = 0

    private var _binding: FragmentRegularIntentSetupBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = RegularIntentViewModel

        _binding = FragmentRegularIntentSetupBinding.inflate(inflater, container, false)
        _root = binding.root

        setup()

        return _root
    }

    private fun setup() {
        dropdown = _root.findViewById<AutoCompleteTextView>(R.id.selectDaysAutoComplete)
        val items = arrayOf("Every day", "Work days", "On weekends")
        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        dropdown.setAdapter(adapter)

        editTextIntentName = _root.findViewById(R.id.editTextIntentName)
        arrivalTimeButton = _root.findViewById(R.id.arrivalTimeButton)

        continueButton = _root.findViewById(R.id.continueButton)
        continueButton.isEnabled = false

        editTextIntentName.setText(viewModel.selectedName.value)
        arrivalTimeButton.text = viewModel.selectedArrivalTime.value
        dropdown.setText(viewModel.selectedDay.value, false)
        //dropdown.setText(viewModel.selectedDay.value)

        setupLocationsRecyclerView()

        val tw: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                viewModel.selectedName.value = editTextIntentName.text.toString()
                updateButtonState()
            }
        }
        editTextIntentName.addTextChangedListener(tw)

        dropdown.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedWord: String = parent.getItemAtPosition(position).toString()
                viewModel.selectedDay.value = selectedWord
                updateButtonState()
            }

        arrivalTimeButton.setOnClickListener {
            popTimePicker(arrivalTimeButton)
        }

        continueButton.setOnClickListener {view ->
            //view.findNavController().navigate(R.id.action_nav_flexible_intent_setup_to_nav_travel_now)
        }
    }

    private fun setupLocationsRecyclerView() {
        val bookmarksRecyclerView: RecyclerView = binding.locationsRecyclerViewId
        val layoutManager =
            LinearLayoutManager(MainActivity.context, LinearLayoutManager.VERTICAL, false)
        if(viewModel.startingPointNewAddress.value != null) {
            viewModel.startingPoint.value = RegularIntentLocation(viewModel.startingPointNewAddress.value?.name,
                "1",
                viewModel.startingPointNewAddress.value?.address,
                ""
            )
            updateButtonState()
        }
        if(viewModel.destinationNewAddress.value != null) {
            viewModel.destination.value = RegularIntentLocation(viewModel.destinationNewAddress.value?.name,
                "2",
                viewModel.destinationNewAddress.value?.address,
                ""
            )
            updateButtonState()
        }
        var locations : ArrayList<RegularIntentLocation> = arrayListOf(
            viewModel.startingPoint.value!!,
            viewModel.destination.value!!
        )
        val adapter = LocationAdapter(
            MainActivity.context,
            locations
        )
        bookmarksRecyclerView.layoutManager = layoutManager
        bookmarksRecyclerView.adapter = adapter
        addMessageDividers(bookmarksRecyclerView, layoutManager)
    }

    private fun addMessageDividers(
        recyclerView: RecyclerView,
        linearLayoutManager: LinearLayoutManager
    ) {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            linearLayoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun popTimePicker(button: Button) {
        val onTimeSetListener =
            TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                hour = selectedHour
                minute = selectedMinute
                button.setText(
                    java.lang.String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        hour,
                        minute
                    )
                )
                viewModel.selectedArrivalTime.value = java.lang.String.format(
                    Locale.getDefault(),
                    "%02d:%02d",
                    hour,
                    minute
                )
                updateButtonState()
            }

        // int style = AlertDialog.THEME_HOLO_DARK;
        val timePickerDialog =
            TimePickerDialog(activity,  /*style,*/onTimeSetListener, hour, minute, true)
        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }

    fun updateButtonState() {
        continueButton.isEnabled = editTextIntentName.text.isNotEmpty() &&
                arrivalTimeButton.text != "00:00" &&
                viewModel.selectedDay.value != "" &&
                viewModel.startingPoint.value?.address != null &&
                viewModel.destination.value?.address != null
    }
}