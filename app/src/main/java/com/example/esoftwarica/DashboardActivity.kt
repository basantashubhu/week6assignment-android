package com.example.esoftwarica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.esoftwarica.fragments.AboutUsFragment
import com.example.esoftwarica.fragments.AddStudentFragment
import com.example.esoftwarica.fragments.HomeFragment
import com.example.esoftwarica.model.Student
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class DashboardActivity : AppCompatActivity() {
    private lateinit var fragContainer: LinearLayout
    private lateinit var tabLayout: BottomNavigationView

    var listStudent = arrayListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        //binding
        tabLayout = findViewById(R.id.tabLayout)
        fragContainer = findViewById(R.id.fragContainer)

        populateStudents()

        if (savedInstanceState == null) {
            loadPage(HomeFragment())
        }

        tabLayout.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.homePage -> loadPage(HomeFragment())
                R.id.aboutUs -> loadPage(AboutUsFragment())
                R.id.addStudent -> loadPage(AddStudentFragment())
                else -> false
            }
        }
    }

    private fun loadPage(fragment: Fragment) : Boolean {
        if (fragment is HomeFragment) {
            fragment.arguments = Bundle().apply {
                putParcelableArrayList("listStudent", listStudent)
            }
        }
        supportFragmentManager.beginTransaction().apply {
            replace(fragContainer.id, fragment)
//            addToBackStack(null)
            commit()
        }
        return true
    }

    private fun populateStudents() {
        listStudent.add(Student("Ram Khatri", "Jorpati", "22", "Male"))
        listStudent.add(Student("Hari Shrestha", "Chabahil", "24", "Male"))
        listStudent.add(Student("Sangita Magar", "Dillibazar", "25", "Female"))
        listStudent.add(Student("Rukmini Pandey", "Gausala", "19", "Female"))
    }


    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(this).apply {
            setIcon(android.R.drawable.ic_dialog_alert)
            setTitle("Closing App")
            setMessage("Are you sure you want to exit?")
            setPositiveButton("Yes") { _,_ ->
                finish()
            }
            setNegativeButton("No", null)
            setCancelable(false)
        }.create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.muted))
        }
        dialog.show()
    }
}