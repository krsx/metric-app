package com.capstone.metricapp.core.ui.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.diffutil.ScadatelListDiffUtil
import com.capstone.metricapp.databinding.ItemListKeypointsBinding

class ScadatelKeypointsAdapter(private val listScadatel: List<Scadatel>) :
    ListAdapter<Scadatel, ScadatelKeypointsAdapter.ViewHolder>(ScadatelListDiffUtil()) {
    inner class ViewHolder(val binding: ItemListKeypointsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private lateinit var onItemCallback: OnItemClickCallback

    override fun getItemCount(): Int {
        return listScadatel.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListKeypointsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scadatel = listScadatel[position]
        holder.apply {
            binding.apply {
                tvListKeypointsRegion.text = scadatel.region
                tvListKeypoints.text = scadatel.keypoint
                tvListKeypointsDate.text = DateUtil.convertDateKeypoints(scadatel.dateCreated)
                Log.e("TEST", DateUtil.convertDateKeypoints(scadatel.dateCreated))
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(listScadatel: List<Scadatel>)
    }
}