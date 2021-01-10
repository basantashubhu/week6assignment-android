package com.example.esoftwarica.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esoftwarica.R
import com.example.esoftwarica.model.Student
import com.example.esoftwarica.adapter.StudentAdapter

class HomeFragment : Fragment() {
    private lateinit var studentRecyclerView: RecyclerView
    private var studentList = arrayListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //binding
        studentRecyclerView = view.findViewById(R.id.student_recycler_view)
        val bundle = arguments
        if (bundle != null) {
            val listStudent = bundle.getParcelableArrayList<Student>("listStudent")
            if (listStudent != null) {
                studentList = listStudent
            }
        }

        studentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = StudentAdapter(context, studentList)
        }

        return view
    }
}