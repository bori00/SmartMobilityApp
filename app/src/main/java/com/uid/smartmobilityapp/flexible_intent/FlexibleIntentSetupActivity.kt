package com.uid.smartmobilityapp.flexible_intent

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.uid.smartmobilityapp.R


class FlexibleIntentSetupActivity : AppCompatActivity() {

    private lateinit var editTextIntentName : EditText
    private lateinit var fromHourText : EditText
    private lateinit var toHourText : EditText
    private lateinit var dropdown : AutoCompleteTextView
    private var selectedDay : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_intent_setup)

        dropdown = findViewById<AutoCompleteTextView>(R.id.selectDayAutoComplete)
        val items = arrayOf("Today", "Tomorrow", "In two days")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        dropdown.setAdapter(adapter)

        editTextIntentName = findViewById(R.id.editTextIntentName)
        fromHourText = findViewById(R.id.fromHourFlexibleIntent)
        toHourText = findViewById(R.id.toHourFlexibleIntent)

        val selectDestinationButton = findViewById<Button>(R.id.selectDestinationButton)
        selectDestinationButton.setOnClickListener {
            val intent = Intent(this, FlexibleIntentSelectTransportActivity::class.java)
            startActivity(intent)
        }
        selectDestinationButton.isEnabled = false
        val tw: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                updateButtonState(selectDestinationButton)
            }
        }

        editTextIntentName.addTextChangedListener(tw)
        fromHourText.addTextChangedListener(tw)
        toHourText.addTextChangedListener(tw)
        dropdown.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val selectedWord: String = parent.getItemAtPosition(position).toString()
            selectedDay = selectedWord
            updateButtonState(selectDestinationButton)
        }

    }

    fun updateButtonState(button: Button) {
        button.isEnabled = editTextIntentName.text.isNotEmpty() &&
                fromHourText.text.isNotEmpty() &&
                toHourText.text.isNotEmpty() &&
                selectedDay != ""
    }

}