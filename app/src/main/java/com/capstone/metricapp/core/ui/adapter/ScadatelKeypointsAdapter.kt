package com.capstone.metricapp.core.ui.adapter

import android.content.Intent
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
import com.capstone.metricapp.features.detail.DetailKeypointActivity

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

        holder.itemView.setOnClickListener {
            onItemCallback.onItemClicked(scadatel)

            val intentToDetailKeypoints =
                Intent(holder.itemView.context, DetailKeypointActivity::class.java)
            intentToDetailKeypoints.putExtra(KEY_ID_SCADATEL, scadatel.uniqueId)
            holder.itemView.context.startActivity(intentToDetailKeypoints)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(listScadatel: Scadatel)
    }

    companion object{
        const val KEY_ID_SCADATEL = "key_id_scadatel"
    }
}