package com.capstone.metricapp.core.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.features.detail.gangguan.DetailIssueFragment
import com.capstone.metricapp.features.detail.historis.DetailHistoryFragment
import com.capstone.metricapp.features.detail.spesifikasi.DetailSpecGIGHFragment
import com.capstone.metricapp.features.detail.spesifikasi.DetailSpecScadatelFragment

class DetailKeypointsSectionsAdapter(activity: AppCompatActivity, var type: KeypointsType) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = when (type) {
                KeypointsType.SCADATEL -> DetailSpecScadatelFragment()
                KeypointsType.GIGH -> DetailSpecGIGHFragment()
                KeypointsType.LBSREC -> DetailSpecGIGHFragment()
            }
            1 -> fragment = DetailHistoryFragment()
            2 -> fragment = DetailIssueFragment()
        }

        return fragment as Fragment
    }


}