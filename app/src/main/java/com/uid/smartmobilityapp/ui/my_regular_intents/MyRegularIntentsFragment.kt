package com.uid.smartmobilityapp.ui.my_regular_intents

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uid.smartmobilityapp.R
import java.text.SimpleDateFormat
import java.util.*
import com.uid.smartmobilityapp.databinding.FragmentMyRegularIntentsBinding

class MyRegularIntentsFragment : Fragment() {

    private var _binding: FragmentMyRegularIntentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(MyRegularIntentsViewModel::class.java)

        _binding = FragmentMyRegularIntentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setDayDropDown()
        setIntentDropDown()
        setNextButton()
        return root
    }

    private fun setNextButton() {
        val nextButton: Button = binding.nextButtonID
        nextButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.view_final_route_regular)
        }
    }
    private fun setIntentDropDown() {
        val dropdown = binding.selectIntentAutoCompleteID
        val items = arrayOf("Supermarket trip", "Uni", "Gym")
        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        dropdown.setAdapter(adapter)

        val tw: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
            }
        }
        dropdown.addTextChangedListener(tw)

        dropdown.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedWord: String = parent.getItemAtPosition(position).toString()
                val selectedDay = selectedWord
            }
    }

    private fun setDayDropDown() {
        val tommorrowCalendar = Calendar.getInstance()
        tommorrowCalendar.add(Calendar.DATE, 1)

        val dateFormat = SimpleDateFormat("EEEE, dd.MM.yyyy")
        val currentDate = Date()
        val today = dateFormat.format(currentDate)
        val tomorrow = dateFormat.format(tommorrowCalendar.time)
        tommorrowCalendar.add(Calendar.DATE, 2)
        val dayAfterTomorrow = dateFormat.format(tommorrowCalendar.time)


        val dropdown = binding.selectDayAutoCompleteID
        val items = arrayOf(today,
            tomorrow,
            dayAfterTomorrow)
        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        dropdown.setAdapter(adapter)

        val tw: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
            }
        }
        dropdown.addTextChangedListener(tw)

        dropdown.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedWord: String = parent.getItemAtPosition(position).toString()
                val selectedDay = selectedWord
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}