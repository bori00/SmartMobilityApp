package com.uid.smartmobilityapp.ui.report_event

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.databinding.FragmentReportEventBinding
import com.uid.smartmobilityapp.ui.flexible_intent.FlexibleIntentViewModel
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer


class ReportEventFragment : Fragment() {

    private var _binding: FragmentReportEventBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel : ReportEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ReportEventViewModel

        _binding = FragmentReportEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupDateTimePickers()
        setupEventTypeDropDown()
        setupLocationView()
        setupReportEventButton()
        setupViewModel()

        return root
    }

    private fun setupReportEventButton() {
        val reportEventButton = binding.reportEventButton

        reportEventButton.setOnClickListener {onReportEventClick()}
    }

    private fun onReportEventClick() {
        if (viewModel.selectedEventType.value == null) {
            Toast.makeText(context, "Please select an event type", Toast.LENGTH_SHORT).show()
            return
        }
        if (viewModel.selectedLocation.value == null) {
            Toast.makeText(context, "Please select a location", Toast.LENGTH_SHORT).show()
            return
        }
        if (viewModel.selectedEndDate.value != null &&
            viewModel.selectedStartDate.value != null &&
            viewModel.selectedEndDate.value!!.isBefore(viewModel.selectedStartDate.value)) {
            Toast.makeText(context, "Please specify an end date after the start date", Toast.LENGTH_SHORT).show()
            return
        }
        binding.root.findNavController().navigate(R.id.action_nav_report_event_to_nav_report_event_success)
    }

    private fun setupDateTimePickers() {
        val startDatePickerButton : Button = binding.btnDate
        startDatePickerButton.setOnClickListener{selectStartDate()}
        viewModel.selectedStartDate.observe(viewLifecycleOwner) {selectedStartDate ->
            if (selectedStartDate != null) {
                binding.startDateEditText1.setText(
                    getDateString(
                        selectedStartDate.dayOfMonth,
                        selectedStartDate.monthValue,
                        selectedStartDate.year,
                        selectedStartDate.hour,
                        selectedStartDate.minute))
            } else {
                binding.startDateEditText1.setText("")
            }
        }

        val endDatePickerButton : Button = binding.btnDate2
        endDatePickerButton.setOnClickListener{selectEndDate()}
        viewModel.selectedEndDate.observe(viewLifecycleOwner) {selectedEndDate ->
            if (selectedEndDate != null) {
                binding.endDateEditText.setText(
                    getDateString(
                        selectedEndDate.dayOfMonth,
                        selectedEndDate.monthValue,
                        selectedEndDate.year,
                        selectedEndDate.hour,
                        selectedEndDate.minute))
            } else {
                binding.endDateEditText.setText("")
            }
        }
    }

    private fun setupLocationView() {
        val locationView = binding.eventLocationView

        locationView.locationNoId.visibility = GONE
        locationView.locationIcon.visibility = VISIBLE
        locationView.deleteLocationButtonId.visibility = GONE

        val editLocationButton = binding.eventLocationView.editLocationButtonId
        editLocationButton.setOnClickListener {onEditLocationButtonClick()}
    }

    private fun onEditLocationButtonClick() {
        binding.root.findNavController().navigate(R.id.action_nav_report_event_to_nav_report_event_select_location)
    }

    private fun setupEventTypeDropDown() {
        val eventTypeDropDown = binding.selectEventTypeAutoComplete

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.event_types_array)
        )
        eventTypeDropDown.setAdapter(adapter)
        if (viewModel.selectedEventType.value != null) {
            eventTypeDropDown.setText(adapter.getItem(adapter.getPosition(viewModel.selectedEventType.value)).toString(), false)
        }

        eventTypeDropDown.setOnItemClickListener { adapterView: AdapterView<*>, _: View, position: Int, _: Long -> onEventTypeSelectedListener(
            adapterView.getItemAtPosition(position) as String?
        )}
    }

    private fun onEventTypeSelectedListener(eventType: String?) {
        viewModel.selectedEventType.value = eventType
    }

    private fun selectDateTime(callback : Consumer<LocalDateTime?>) {
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val mHour = c.get(Calendar.HOUR_OF_DAY);
        val mMinute = c.get(Calendar.MINUTE);


        val datePickerDialog = DatePickerDialog(requireContext(),
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                run {

                    val timePickerDialog = TimePickerDialog(
                        requireContext(),
                        OnTimeSetListener { view, hourOfDay, minute ->

                            val selectedDateTime = LocalDateTime.of(year, monthOfYear + 1, dayOfMonth, hourOfDay, minute)
                            callback.accept(selectedDateTime)
                        },
                        mHour,
                        mMinute,
                        false
                    )
                    timePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Clear"
                    ) { dialog, which -> callback.accept(null) }
                    timePickerDialog.setButton(DatePickerDialog.BUTTON_NEUTRAL, "Discard"
                    ) { dialog, which -> {} }
                    timePickerDialog.show()
                }
            },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Clear"
        ) { dialog, which -> callback.accept(null) }
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEUTRAL, "Discard"
        ) { dialog, which -> {} }
        datePickerDialog.show()
    }

    private fun selectStartDate() {

        Log.d("ReportEventFragment", "Select Start Date")

        val onDateSelected = Consumer { localDateTime: LocalDateTime? ->
            run {
                Log.d("ReportEventFragment", "Selected Start Date: " + localDateTime.toString())
                viewModel.selectedStartDate.value = localDateTime
            }
        }

        selectDateTime(onDateSelected)
    }

    private fun selectEndDate() {
        Log.d("ReportEventFragment", "Select End Date")

        val onDateSelected = Consumer { localDateTime: LocalDateTime? ->
            run {
                Log.d("ReportEventFragment", "Selected End Date: " + localDateTime.toString())
                viewModel.selectedEndDate.value = localDateTime
            }
        }

        selectDateTime(onDateSelected)
    }

    private fun getDateString(dayOfMonth : Int, month : Int, year : Int, hourOfDay : Int, minute : Int) : String {
        return "%02d-%02d-%04d %02d:%02d".format(dayOfMonth, month, year, hourOfDay, minute)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        // Address selection
        viewModel.selectedLocation.observe(viewLifecycleOwner) {
            Log.d("ReportEventFragment", "Updated selected location: $it")
            if (it == null) {
                binding.eventLocationView.LocationNameTFId.setText("Unknown")
            } else {
                binding.eventLocationView.LocationNameTFId.setText(it.name)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        setupEventTypeDropDown()
    }
}