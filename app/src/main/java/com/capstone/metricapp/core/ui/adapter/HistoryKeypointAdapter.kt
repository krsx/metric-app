package com.capstone.metricapp.core.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.metricapp.R
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.core.utils.datamapper.RTUDataMapper
import com.capstone.metricapp.core.utils.datamapper.ScadatelDataMapper
import com.capstone.metricapp.core.utils.diffutil.ScadatelHistoryDiffUtil
import com.capstone.metricapp.databinding.ItemListSpecChangeBinding
import com.capstone.metricapp.features.detail.historis.historis_spec_detail.DetailHistorySpecGIGH
import com.capstone.metricapp.features.detail.historis.historis_spec_detail.DetailHistorySpecLBSREC
import com.capstone.metricapp.features.detail.historis.historis_spec_detail.DetailHistorySpecScadatelFragment

class HistoryKeypointAdapter(
    private val listHistory: List<KeypointHistory>,
    private val fragmentManager: FragmentManager,
    private val keypointType: KeypointsType
) :
    ListAdapter<KeypointHistory, HistoryKeypointAdapter.ViewHolder>(ScadatelHistoryDiffUtil()) {

    private lateinit var onItemCallback: OnItemClickCallback

    inner class ViewHolder(val binding: ItemListSpecChangeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return listHistory.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListSpecChangeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = listHistory[position]

        holder.apply {
            binding.apply {
                when (keypointType) {
                    KeypointsType.LBSREC -> {
                        tvListSpecChangeDate.text =
                            DateUtil.convertDateKeypoints(RTUDataMapper.getHistoryDateOnly(history.newValue))
                    }
                    KeypointsType.GIGH -> {
                        tvListSpecChangeDate.text =
                            DateUtil.convertDateKeypoints(RTUDataMapper.getHistoryDateOnly(history.newValue))
                    }
                    KeypointsType.SCADATEL -> {
                        tvListSpecChangeDate.text =
                            DateUtil.convertDateKeypoints(
                                ScadatelDataMapper.getHistoryDateOnly(history.newValue)
                            )
                    }
                }
                tvListSpecChange.text =
                    holder.itemView.context.getString(R.string.change_spec, position + 1)
            }
        }

        holder.itemView.setOnClickListener {
            when (keypointType) {
                KeypointsType.LBSREC -> {
                    DetailHistorySpecLBSREC(history).show(
                        fragmentManager,
                        LBSREC_HISTORY_FRAGMENT_TAG
                    )
                }
                KeypointsType.GIGH -> {
                    DetailHistorySpecGIGH(history).show(
                        fragmentManager,
                        GIGH_HISTORY_FRAGMENT_TAG
                    )
                }
                KeypointsType.SCADATEL -> {
                    DetailHistorySpecScadatelFragment(history).show(
                        fragmentManager,
                        SCADATEL_HISTORY_FRAGMENT_TAG
                    )
                }
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(history: KeypointHistory)
    }

    companion object {
        private const val SCADATEL_HISTORY_FRAGMENT_TAG = "Detail History Scadatel"
        private const val GIGH_HISTORY_FRAGMENT_TAG = "Detail History Scadatel"
        private const val LBSREC_HISTORY_FRAGMENT_TAG = "Detail History Scadatel"

    }
}