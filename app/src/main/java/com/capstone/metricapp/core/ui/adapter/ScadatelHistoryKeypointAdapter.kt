package com.capstone.metricapp.core.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.metricapp.R
import com.capstone.metricapp.core.domain.model.ScadatelHistory
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.datamapper.ScadatelDataMapper
import com.capstone.metricapp.core.utils.diffutil.ScadatelHistoryDiffUtil
import com.capstone.metricapp.databinding.ItemListSpecChangeBinding

class ScadatelHistoryKeypointAdapter(private val listHistory: List<ScadatelHistory>) :
    ListAdapter<ScadatelHistory, ScadatelHistoryKeypointAdapter.ViewHolder>(ScadatelHistoryDiffUtil()) {

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
                tvListSpecChangeDate.text =
                    DateUtil.convertDateKeypoints(ScadatelDataMapper.getHistoryDateOnly(history.newValue))
                tvListSpecChange.text =
                    holder.itemView.context.getString(R.string.change_spec, position)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(history: ScadatelHistory)
    }

    companion object {
        const val KEY_POSITION_HISTORY = "key_position_history"
    }
}