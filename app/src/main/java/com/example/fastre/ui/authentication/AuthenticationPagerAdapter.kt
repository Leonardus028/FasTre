package com.example.fastre.ui.authentication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

internal class AuthenticationPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    private val fragmentList = ArrayList<Fragment>()
    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragmet(fragment: Fragment) {
        fragmentList.add(fragment)
    }

}