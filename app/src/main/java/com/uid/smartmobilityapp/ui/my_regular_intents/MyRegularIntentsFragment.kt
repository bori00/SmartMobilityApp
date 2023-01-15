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
    lateinit private var _viewModel: MyRegularIntentsViewModel
    private var intentSelected = false
    private var dateSelected = false
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel =
            ViewModelProvider(this).get(MyRegularIntentsViewModel::class.java)

        _binding = FragmentMyRegularIntentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setDayDropDown()
        setIntentDropDown()
        setAddNewIntentButtonButton()
        setNextButton()
        return root
    }

    private fun setNextButton() {
        val nextButton: Button = binding.nextButtonID
        nextButton.isEnabled = false
        nextButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.view_final_route_regular)
        }
    }
    private fun setAddNewIntentButtonButton() {
        val nextButton: Button = binding.addNewIntentButtonID
        nextButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.view_final_route_to_new_regular)
        }
    }

    private fun enableButton() {
        val nextButton: Button = binding.nextButtonID
        nextButton.isEnabled = true
    }

    private fun setIntentDropDown() {
        val dropdown = binding.selectIntentAutoCompleteID
        val items = ArrayList(_viewModel.intents.value!!)
        val itemNames = items.map( {reg ->
            reg.name
        });
        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, itemNames) }
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
                intentSelected = true
                if(dateSelected) enableButton()
            }
    }

    private fun setDayDropDown() {
        val dropdown = binding.selectDayAutoCompleteID
        val items = arrayOf(
            "Wednesday, 18.01.2023",
            "Thursday, 19.01.2023",
            "Friday, 20.01.2023"
        )
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
                dateSelected = true
                if(intentSelected) enableButton()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}