package com.uid.smartmobilityapp.ui.profile_setup

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentProfileSetupBinding
import java.util.*
import java.util.function.Consumer

class ProfileSetupFragment : Fragment() {
    private var _binding: FragmentProfileSetupBinding? = null
    lateinit private var _viewModel: ProfileSetupViewModel
    lateinit private var _root: View;

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel =
            ViewModelProvider(this).get(ProfileSetupViewModel::class.java)

        _binding = FragmentProfileSetupBinding.inflate(inflater, container, false)
        _root = binding.root

        setupSaveButton()
        setupCancelButton()
        setupDateTimePickers()
        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun selectStartDate() {

        val onDateSelected = Consumer { localDateTime: Date? ->
            run {
                if (localDateTime != null) {
                    Log.d("date picked", localDateTime.toString())
                    _viewModel.user.value!!.expiryDate = localDateTime
                }
            }
        }

        selectDateTime(onDateSelected)
    }

    private fun selectDateTime(callback: Consumer<Date?>) {
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            MainActivity.context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.setButton(
            DatePickerDialog.BUTTON_NEGATIVE, "Clear"
        ) { dialog, which -> callback.accept(null) }
        datePickerDialog.setButton(
            DatePickerDialog.BUTTON_NEUTRAL, "Discard"
        ) { dialog, which -> {} }
        datePickerDialog.show()
    }

    private fun setupDateTimePickers() {
        val expiryDatePickerButton: Button = binding.selectDateButtonID

        expiryDatePickerButton.setOnClickListener { selectStartDate() }
        _viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user.expiryDate != null) {
                binding.expiryDayTextID.setText(
                    user.expiryDate.toString(),
                )
            } else {
                binding.expiryDayTextID.setText("")
            }
        }

    }


    private fun setupSaveButton() {
        val saveButton: Button = binding.saveButtonID
        saveButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_setup_profile_to_home)
        }
    }

    private fun setupCancelButton() {
        val cancelButton: Button = binding.cancelButtonID
        cancelButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_setup_profile_to_home)
        }
    }
}