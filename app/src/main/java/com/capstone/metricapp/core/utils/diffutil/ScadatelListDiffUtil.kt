package com.capstone.metricapp.core.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.capstone.metricapp.core.domain.model.Scadatel

class ScadatelListDiffUtil : DiffUtil.ItemCallback<Scadatel>() {
    override fun areItemsTheSame(oldItem: Scadatel, newItem: Scadatel): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Scadatel, newItem: Scadatel): Boolean {
        return when {
            oldItem.id != newItem.id -> false
            oldItem.os != newItem.os -> false
            oldItem.date != newItem.date -> false
            oldItem.backupVolt != newItem.backupVolt -> false
            oldItem.dateCreated != newItem.dateCreated -> false
            oldItem.keypoint != newItem.keypoint -> false
            oldItem.mainVolt != newItem.mainVolt -> false
            oldItem.merk != newItem.merk -> false
            oldItem.region != newItem.region -> false
            oldItem.type != newItem.type -> false
            oldItem.uniqueId != newItem.uniqueId -> false
            else -> true
        }
    }

}