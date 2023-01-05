package com.uid.smartmobilityapp.ui.profile_setup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.databinding.FragmentProfileSetupBinding
import com.uid.smartmobilityapp.ui.profile_setup.model.MyUser
import com.uid.smartmobilityapp.ui.profile_setup.model.UserModel
import java.time.LocalDateTime
import java.time.Month
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

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

        setupCancelButton()
        setupFields()
        setupDateTimePickers()
        setupSaveButton()
        return _root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun selectStartDate() {
        val onDateSelected = Consumer { localDateTime: LocalDateTime? ->
            run {
                if (localDateTime != null) {
                    _viewModel.user.value!!.expiryDate = localDateTime
                    binding.expiryDayTextID.setText(
                        _viewModel.user.value!!.expiryDate.toString(),
                    )
                }
            }
        }

        selectDateTime(onDateSelected)
    }

    private fun selectDateTime(callback: Consumer<LocalDateTime?>) {
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val mHour = c.get(Calendar.HOUR_OF_DAY);
        val mMinute = c.get(Calendar.MINUTE);

        val datePickerDialog = DatePickerDialog(
            MainActivity.context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                run {

                    val timePickerDialog = TimePickerDialog(
                        MainActivity.context,
                        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                            val selectedDateTime = LocalDateTime.of(
                                year,
                                monthOfYear + 1,
                                dayOfMonth,
                                hourOfDay,
                                minute
                            )
                            callback.accept(selectedDateTime)
                        },
                        mHour,
                        mMinute,
                        false
                    )
                    timePickerDialog.setButton(
                        DatePickerDialog.BUTTON_NEGATIVE, "Clear"
                    ) { dialog, which -> callback.accept(null) }
                    timePickerDialog.setButton(
                        DatePickerDialog.BUTTON_NEUTRAL, "Discard"
                    ) { dialog, which -> {} }
                    timePickerDialog.show()
                }
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
        val radioButton: RadioButton = binding.busNoID
        val expEdit: EditText = binding.expiryDayTextID

        expEdit.isEnabled = false
        expiryDatePickerButton.isEnabled = _viewModel.user.value!!.hasPublicTransportPass

        radioButton.setOnCheckedChangeListener { _, isChecked ->
            expiryDatePickerButton.isEnabled = !isChecked
        }
        expiryDatePickerButton.setOnClickListener { selectStartDate() }
    }

    private fun setupFields() {


        val bikeYes = binding.bikeYesID
        val bikeNo = binding.bikeNoID

        val scooterYes = binding.scooterYesID
        val scooterNo = binding.scooterNoID

        val busYes = binding.busYesID
        val busNo = binding.busNoID

        val carYes = binding.carYesID
        val carNo = binding.carNoID

        val maxDis: EditText = binding.maximumDistEditTextID
        val expDate: EditText = binding.expiryDayTextID
        val bike = binding.bikeCheckID
        val car = binding.carCheckID
        val scooter = binding.scooterCheckID

        setCheckboxListener(bike)
        setCheckboxListener(car)
        setCheckboxListener(scooter)

        maxDis.setText(_viewModel.user.value!!.maxWalkingDistance)

        if (_viewModel.user.value!!.expiryDate != LocalDateTime.of(
                2000,
                Month.JANUARY,
                1,
                10,
                30,
                0
            )
        ) {
            expDate.setText(_viewModel.user.value!!.expiryDate.toString())
        } else {
            expDate.setText("")
        }

        if (_viewModel.user.value!!.vehicleList.contains("Car")) {
            car.isChecked = true
        }
        if (_viewModel.user.value!!.vehicleList.contains("Bike")) {
            bike.isChecked = true
        }
        if (_viewModel.user.value!!.vehicleList.contains("Scooter")) {
            scooter.isChecked = true
        }
        if (_viewModel.user.value!!.canRideScooter) {
            scooterYes.isChecked = true
        } else {
            scooterNo.isChecked = true
        }

        if (_viewModel.user.value!!.canRideBike) {
            bikeYes.isChecked = true
        } else {
            bikeNo.isChecked = true
        }

        if (_viewModel.user.value!!.hasPublicTransportPass) {
            busYes.isChecked = true
        } else {
            busNo.isChecked = true
        }

        if (_viewModel.user.value!!.hasDrivingLicense) {
            carYes.isChecked = true
        } else {
            carNo.isChecked = true
        }


    }

    private fun setupSaveButton() {
        val saveButton: Button = binding.saveButtonID
        val bikeYes = binding.bikeYesID
        val scooterYes = binding.scooterYesID
        val busYes = binding.busYesID
        val carYes = binding.carYesID
        val maxDis: EditText = binding.maximumDistEditTextID
        val expDate: EditText = binding.expiryDayTextID

        saveButton.setOnClickListener {
            if (checkWalkingDistance()) {
                MyUser.defaultUser.hasDrivingLicense = carYes.isChecked
                MyUser.defaultUser.canRideBike = bikeYes.isChecked
                MyUser.defaultUser.canRideScooter = scooterYes.isChecked
                MyUser.defaultUser.maxWalkingDistance = maxDis.text.toString()
                MyUser.defaultUser.hasPublicTransportPass = busYes.isChecked
                MyUser.defaultUser.expiryDate = LocalDateTime.parse(expDate.text.toString())
                binding.root.findNavController().navigate(R.id.action_setup_profile_to_home)
            }
        }
    }

    private fun setCheckboxListener(checkBox: CheckBox) {
        checkBox.setOnClickListener {
            updateModelState(checkBox)
        }
    }

    private fun updateModelState(value: CheckBox) {
        val valueOfCheckBox = value.text.toString()
        if (value.isChecked) {
            var list = MyUser.defaultUser.vehicleList
            list += valueOfCheckBox
            MyUser.defaultUser.vehicleList = list.toSet().toMutableList() as ArrayList<String>
        } else {
            var list = MyUser.defaultUser.vehicleList
            list -= valueOfCheckBox
            MyUser.defaultUser.vehicleList = list.toSet().toMutableList() as ArrayList<String>

        }
    }

    private fun checkWalkingDistance(): Boolean {
        val walkingDistance: EditText = binding.maximumDistEditTextID
        val number = walkingDistance.text.toString().toIntOrNull()
        if (number == null || number <= 0) {
            walkingDistance.error = "Please enter a valid number"
            return false
        }
        walkingDistance.error = null
        return true
    }

    private fun setupCancelButton() {
        val cancelButton: Button = binding.cancelButtonID
        cancelButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_setup_profile_to_home)
        }
    }
}