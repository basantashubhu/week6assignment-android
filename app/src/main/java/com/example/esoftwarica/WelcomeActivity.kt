package com.example.esoftwarica

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.esoftwarica.adapter.ViewPagerAdapter
import com.example.esoftwarica.fragments.WelcomeFragmentOne
import com.example.esoftwarica.fragments.WelcomeFragmentTwo
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WelcomeActivity : AppCompatActivity() {
    private lateinit var tabs : TabLayout
    private lateinit var pageContainer : ViewPager2
    private lateinit var mainContainer : ConstraintLayout

    private lateinit var skipBtn : Button
    private lateinit var nextBtn : Button

    private val listFragments = arrayListOf<Fragment>()
    private val listColors = arrayListOf<Int>()
    private val listIcons = arrayListOf<Drawable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        //binding
        mainContainer = findViewById(R.id.mainContainerLayout)
        skipBtn = findViewById(R.id.skip_btn)
        nextBtn = findViewById(R.id.next_btn)
        tabs = findViewById(R.id.tabs)
        pageContainer = findViewById(R.id.pageContainer)
        pageContainer.adapter = ViewPagerAdapter(listFragments, supportFragmentManager, lifecycle)

        populateFrags()

        TabLayoutMediator(tabs, pageContainer) { tab, position ->
            tab.icon = listIcons[position]
        }.attach()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mainContainer.setBackgroundColor(listColors[tab?.position?:0])
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        skipBtn.setOnClickListener {
            loginActivity()
        }
        nextBtn.setOnClickListener {
            val tab = tabs.selectedTabPosition
            if (tab < tabs.tabCount-1) {
                tabs.getTabAt(tab + 1)?.select()
            } else {
                loginActivity()
            }
        }
    }

    private fun loginActivity() {
        val loginActivity = Intent(this, LoginActivity::class.java)
        startActivity(loginActivity)
        finish()
    }

    private fun populateFrags() {
        listIcons.add(ContextCompat.getDrawable(this, R.drawable.ic_point_24)!!)
        listIcons.add(ContextCompat.getDrawable(this, R.drawable.ic_point_24)!!)

        listColors.add(ContextCompat.getColor(this, R.color.info))
        listColors.add(ContextCompat.getColor(this, R.color.success))

        listFragments.add(WelcomeFragmentOne())
        listFragments.add(WelcomeFragmentTwo())
    }
}