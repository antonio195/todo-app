package com.antoniocostadossantos.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniocostadossantos.todoapp.databinding.ActivityMainBinding
import com.antoniocostadossantos.todoapp.ui.adapter.FragmentsAdapter
import com.antoniocostadossantos.todoapp.ui.newtask.NewTaskBottomSheet
import com.antoniocostadossantos.todoapp.ui.tasks.TasksFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: FragmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupFAB()
    }

    private fun setupFAB() {
        binding.fab.setOnClickListener {
            NewTaskBottomSheet().show(supportFragmentManager, "")
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter = FragmentsAdapter(
            supportFragmentManager,
            lifecycle,
            listOf(
                TasksFragment(),
                TasksFragment().apply {
                    arguments = Bundle().apply { putBoolean("CONCLUDED", true) }
                })
        )

        val tabLayout = binding.tablayout
        val viewPager = binding.viewpager
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Not Concluded"
                1 -> tab.text = "Concluded"
            }
        }.attach()
    }
}