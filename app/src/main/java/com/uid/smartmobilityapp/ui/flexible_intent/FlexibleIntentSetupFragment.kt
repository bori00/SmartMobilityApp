package com.uid.smartmobilityapp.ui.flexible_intent

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentFlexibleIntentSetupBinding

import com.uid.smartmobilityapp.ui.travel_now.LocationsViewModel

import java.util.*


class FlexibleIntentSetupFragment : Fragment() {

    private lateinit var editTextIntentName : EditText
    private lateinit var dropdown : AutoCompleteTextView
    private lateinit var fromHourButton: Button
    private lateinit var toHourButton: Button
    private lateinit var selectDestinationButton: Button
    private var hour : Int = 0
    private var minute : Int = 0
    private var selectedDay : String = ""

    private lateinit var viewModel: FlexibleIntentViewModel
    private lateinit var viewModelForLocations: LocationsViewModel
    private var _binding: FragmentFlexibleIntentSetupBinding ? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = FlexibleIntentViewModel
        viewModelForLocations = LocationsViewModel

        _binding = FragmentFlexibleIntentSetupBinding.inflate(inflater, container, false)
        _root = binding.root

        setup()

        return _root
    }

    private fun setup() {
        viewModelForLocations.selectedIntent.value = "Flexible Intent"

        dropdown = _root.findViewById<AutoCompleteTextView>(R.id.selectDayAutoComplete)
        val items = arrayOf("Today", "Tomorrow", "In two days")
        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        dropdown.setAdapter(adapter)

        editTextIntentName = _root.findViewById(R.id.editTextIntentName)
        fromHourButton = _root.findViewById(R.id.fromHourButton)
        toHourButton = _root.findViewById(R.id.toHourButton)

        selectDestinationButton = _root.findViewById(R.id.selectDestinationButton)
        selectDestinationButton.isEnabled = false

        val tw: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                updateButtonState()
            }
        }
        editTextIntentName.addTextChangedListener(tw)

        dropdown.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedWord: String = parent.getItemAtPosition(position).toString()
                selectedDay = selectedWord
                updateButtonState()
            }

        fromHourButton.setOnClickListener {
            popTimePicker(fromHourButton)
        }

        toHourButton.setOnClickListener {
            popTimePicker(toHourButton)
        }

        selectDestinationButton.setOnClickListener {view ->
            viewModel.selectedName.value = editTextIntentName.text.toString()
            viewModel.selectedDay.value = editTextIntentName.text.toString()
            viewModel.selectedFromHour.value = editTextIntentName.text.toString()
            viewModel.selectedToHour.value = editTextIntentName.text.toString()
            view.findNavController().navigate(R.id.action_nav_flexible_intent_setup_to_nav_travel_now)
        }
    }

    fun updateButtonState() {
        selectDestinationButton.isEnabled = editTextIntentName.text.isNotEmpty() &&
                fromHourButton.text != "__:__" &&
                toHourButton.text != "__:__" &&
                selectedDay != ""
    }

    private fun popTimePicker(button: Button) {
        val onTimeSetListener =
            OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
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
                updateButtonState()
            }

        // int style = AlertDialog.THEME_HOLO_DARK;
        val timePickerDialog =
            TimePickerDialog(activity,  /*style,*/onTimeSetListener, hour, minute, true)
        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }

}