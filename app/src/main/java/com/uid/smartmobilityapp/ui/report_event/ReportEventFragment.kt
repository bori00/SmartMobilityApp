package com.uid.smartmobilityapp.ui.report_event

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.databinding.FragmentReportEventBinding
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer


class ReportEventFragment : Fragment() {

    private var _binding: FragmentReportEventBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[ReportEventViewModel::class.java]

        _binding = FragmentReportEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val startDatePickerButton : Button = binding.btnDate
        startDatePickerButton.setOnClickListener{selectStartDate()}

        val endDatePickerButton : Button = binding.btnDate2
        endDatePickerButton.setOnClickListener{selectEndDate()}
        return root
    }

    private fun selectDateTime(callback : Consumer<LocalDateTime>) {
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val mHour = c.get(Calendar.HOUR_OF_DAY);
        val mMinute = c.get(Calendar.MINUTE);


        val datePickerDialog = DatePickerDialog(MainActivity.context,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                run {

                    val timePickerDialog = TimePickerDialog(
                        MainActivity.context,
                        OnTimeSetListener { view, hourOfDay, minute ->

                            val selectedDateTime = LocalDateTime.of(year, monthOfYear + 1, dayOfMonth, hourOfDay, minute)
                            callback.accept(selectedDateTime)
                        },
                        mHour,
                        mMinute,
                        false
                    )
                    timePickerDialog.show()
                }
            },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.show()
    }

    private fun selectStartDate() {

        Log.d("ReportEventFragment", "Select Start Date")

        val onDateSelected = Consumer { localDateTime: LocalDateTime ->
            run {
                Log.d("ReportEventFragment", "Selected Start Date: " + localDateTime.toString())
                binding.startDateEditText1.setText(
                    getDateString(
                        localDateTime.dayOfMonth,
                        localDateTime.monthValue,
                        localDateTime.year,
                        localDateTime.hour,
                        localDateTime.minute
                    )
                )
            }
        }

        selectDateTime(onDateSelected)
    }

    private fun selectEndDate() {
        Log.d("ReportEventFragment", "Select End Date")

        val onDateSelected = Consumer { localDateTime: LocalDateTime ->
            run {
                Log.d("ReportEventFragment", "Selected End Date: " + localDateTime.toString())
                binding.endDateEditText.setText(
                    getDateString(
                        localDateTime.dayOfMonth,
                        localDateTime.monthValue,
                        localDateTime.year,
                        localDateTime.hour,
                        localDateTime.minute
                    )
                )
            }
        }

        selectDateTime(onDateSelected)
    }

    private fun getDateString(dayOfMonth : Int, month : Int, year : Int, hourOfDay : Int, minute : Int) : String {
        return dayOfMonth.toString() + "-" + month+ "-" + year + " " + hourOfDay + ":" + minute
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}