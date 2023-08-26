package com.capstone.metricapp.core.ui.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.diffutil.RTUListDiffUtil
import com.capstone.metricapp.databinding.ItemListKeypointsBinding
import com.capstone.metricapp.features.detail.DetailKeypointActivity

class RTUKeypointsAdapter(private val listRTU: List<RTU>) :
    ListAdapter<RTU, RTUKeypointsAdapter.ViewHolder>(RTUListDiffUtil()) {

    private lateinit var onItemCallback: OnItemClickCallback

    inner class ViewHolder(val binding: ItemListKeypointsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListKeypointsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rtu = listRTU[position]
        holder.apply {
            binding.apply {
                tvListKeypoints.text = rtu.keypoint
                tvListKeypointsRegion.text = rtu.region
                tvListKeypointsDate.text = DateUtil.convertDateKeypoints(rtu.dateCreated)
            }
        }

        holder.itemView.setOnClickListener {
            onItemCallback.onItemClicked(rtu)

            val intentToDetailKeypoints =
                Intent(holder.itemView.context, DetailKeypointActivity::class.java)
            intentToDetailKeypoints.putExtra(
                ScadatelKeypointsAdapter.KEY_ID_KEYPOINTS,
                rtu.uniqueId
            )
            holder.itemView.context.startActivity(intentToDetailKeypoints)
        }
    }

    override fun getItemCount(): Int {
        return listRTU.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(listScadatel: RTU)
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}