package com.uid.smartmobilityapp.ui.rate_ride

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.databinding.FragmentRateRideBinding
import com.uid.smartmobilityapp.services.RideFinderService
import java.time.LocalDate
import java.util.*
import java.util.function.Consumer

class RateRideFragment : Fragment() {

    private var _binding: FragmentRateRideBinding? = null

    lateinit private var viewModel : RateRideViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = RateRideViewModel

        _binding = FragmentRateRideBinding.inflate(inflater, container, false)
        val root: View = binding.root


        setupDateSelection()
        setupRideSelection()
        binding.rateRideButton.setOnClickListener{onSubmitRating()}

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onSubmitRating() {
        if (viewModel.selectedDate.value == null) {
            Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show()
            return
        }
        if (viewModel.selectedRide.value == null) {
            Toast.makeText(context, "Please select a ride", Toast.LENGTH_SHORT).show()
            return
        }
        binding.root.findNavController().navigate(com.uid.smartmobilityapp.R.id.action_nav_rate_ride_to_nav_rate_ride_success)
    }

    private fun updateRateButtonState() {
        binding.rateRideButton.isEnabled =
            viewModel.selectedRide.value != null &&
            viewModel.selectedDate.value != null
    }

    private fun setupRideSelection() {
        val ridesDropDown = binding.selectRideAutoComplete

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            UserActivity.context,
            R.layout.simple_dropdown_item_1line,
            RideFinderService().findRidesOnDate(viewModel.selectedDate.value)
        )
        ridesDropDown.setAdapter(adapter)

        ridesDropDown.setOnItemClickListener { adapterView: AdapterView<*>, _: View, position: Int, _: Long -> onRideSelectedListener(
            adapterView.getItemAtPosition(position) as String?
        )}

        viewModel.selectedDate.observe(viewLifecycleOwner) {selectDate ->
            if (selectDate != null) {
                adapter.clear()
                for (ride in RideFinderService().findRidesOnDate(selectDate)) {
                    adapter.add(ride)
                }
                if (!RideFinderService().findRidesOnDate(selectDate).contains(viewModel.selectedRide.value)) {
                    viewModel.selectedRide.value = null;
                    ridesDropDown.clearListSelection()
                }
                ridesDropDown.isEnabled = true
                adapter.notifyDataSetChanged()
            } else {
                adapter.clear()
                viewModel.selectedRide.value = null;
                ridesDropDown.clearListSelection()
                ridesDropDown.isEnabled = false
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun onRideSelectedListener(ride : String?) {
        Log.d("RateRideFragment", "Ride selected: " + ride)
        viewModel.selectedRide.value = ride
        updateRateButtonState()
    }

    private fun setupDateSelection() {
        viewModel.selectedDate.observe(viewLifecycleOwner) {selectedDate ->
            if (selectedDate != null) {
                binding.startDateEditText1.setText(
                    getDateString(
                        selectedDate.dayOfMonth,
                        selectedDate.monthValue,
                        selectedDate.year))
            } else {
                binding.startDateEditText1.setText("")
            }
            updateRateButtonState()
        }

        binding.btnDate.setOnClickListener{selectDate({date -> viewModel.selectedDate.value = date})}
    }

    private fun getDateString(dayOfMonth : Int, month : Int, year : Int) : String {
        return "$dayOfMonth-$month-$year"
    }

    private fun selectDate(callback : Consumer<LocalDate?>) {
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            UserActivity.context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                run {
                    callback.accept(LocalDate.of(year, monthOfYear + 1, dayOfMonth))
                }
            },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.show()
    }
}