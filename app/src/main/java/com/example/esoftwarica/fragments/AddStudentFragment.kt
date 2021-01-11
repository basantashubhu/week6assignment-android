package com.example.esoftwarica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.esoftwarica.DashboardActivity
import com.example.esoftwarica.R
import com.example.esoftwarica.model.Student

class AddStudentFragment : Fragment() {
    private lateinit var fullNameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var selectGenderTextView: TextView
    private lateinit var saveStudentButton: Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)
        //binding
        fullNameEditText = view.findViewById(R.id.student_name_input)
        ageEditText = view.findViewById(R.id.student_age_input)
        addressEditText = view.findViewById(R.id.student_address_input)
        genderRadioGroup = view.findViewById(R.id.gender_input)
        selectGenderTextView = view.findViewById(R.id.select_gender_text)
        saveStudentButton = view.findViewById(R.id.save_student_btn)

        saveStudentButton.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val dash = activity as DashboardActivity
            dash.listStudent.add(Student(
                    fullNameEditText.text.toString().trimEnd(' '),
                    addressEditText.text.toString().trimEnd(' '),
                    ageEditText.text.toString().trimEnd(' '),
                    view.findViewById<RadioButton>(genderRadioGroup.checkedRadioButtonId)?.text.toString()
            ))

            clearStudentForm()
            Toast.makeText(context, "Successfully Saved", Toast.LENGTH_SHORT).show()
        }

        genderRadioGroup.setOnCheckedChangeListener { _, _ ->
            selectGenderTextView.setError(null)
        }

        return view
    }

    private fun validate() : Boolean{
        var validation = true
        if (fullNameEditText.text.isEmpty()) {
            fullNameEditText.setError("Name is required")
            validation = false
        }
        if (ageEditText.text.isEmpty()) {
            ageEditText.setError("Age is required")
            validation = false
        }
        if (addressEditText.text.isEmpty()) {
            addressEditText.setError("Address is required")
            validation = false
        }
        if (genderRadioGroup.checkedRadioButtonId == -1) {
            selectGenderTextView.setError("Gender is required")
            validation = false
        }
        return validation
    }

    private fun clearStudentForm() {
        fullNameEditText.setText("")
        ageEditText.setText("")
        addressEditText.setText("")
        genderRadioGroup.clearCheck()
    }
}