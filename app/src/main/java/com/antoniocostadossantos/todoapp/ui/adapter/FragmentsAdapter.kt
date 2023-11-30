package com.antoniocostadossantos.todoapp.ui.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentsAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private var fragments: List<Fragment>

) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        Log.i("TESTE", fragments.size.toString())
        return when (position) {
            0 -> fragments[0]
            1 -> fragments[1]
            else -> fragments[0]
        }
    }
}