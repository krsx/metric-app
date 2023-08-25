package com.capstone.metricapp.core.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.capstone.metricapp.core.domain.model.RTU

class RTUListDiffUtil : DiffUtil.ItemCallback<RTU>() {
    override fun areItemsTheSame(oldItem: RTU, newItem: RTU): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: RTU, newItem: RTU): Boolean {
        return when {
            oldItem.id != newItem.id -> false
            oldItem.dateCreated != newItem.dateCreated -> false
            oldItem.uniqueId != newItem.uniqueId -> false
            oldItem.keypoint != newItem.keypoint -> false
            oldItem.region != newItem.region -> false
            oldItem.telkom_date != newItem.telkom_date -> false
            oldItem.telkom_merk != newItem.telkom_merk -> false
            oldItem.telkom_sn != newItem.telkom_sn -> false
            oldItem.telkom_type != newItem.telkom_type -> false
            oldItem.telkom_rangeVolt != newItem.telkom_rangeVolt -> false
            oldItem.main_sim_provider != newItem.main_sim_provider -> false
            oldItem.backup_sim_number != newItem.backup_sim_number -> false
            oldItem.rtu_sn != newItem.rtu_sn -> false
            oldItem.rtu_date != newItem.rtu_date -> false
            oldItem.rtu_merk != newItem.rtu_merk -> false
            oldItem.rtu_type != newItem.rtu_type -> false
            oldItem.bat_date != newItem.bat_date -> false
            oldItem.bat_merk != newItem.bat_merk -> false
            oldItem.bat_type != newItem.bat_type -> false
            oldItem.rect_date != newItem.rect_date -> false
            oldItem.rect_merk != newItem.rect_merk -> false
            oldItem.rect_sn != newItem.rect_sn -> false
            oldItem.rect_type != newItem.rect_type -> false
            oldItem.rect_rangeVolt != newItem.rect_rangeVolt -> false
            oldItem.gat_merk != newItem.gat_merk -> false
            oldItem.gat_date != newItem.gat_date -> false
            oldItem.gat_type != newItem.gat_type -> false
            oldItem.gat_sn != newItem.gat_sn -> false
            else -> true
        }
    }
}