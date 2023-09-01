package com.capstone.metricapp.core.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.capstone.metricapp.core.domain.model.ScadatelHistory

class ScadatelHistoryDiffUtil : DiffUtil.ItemCallback<ScadatelHistory>() {
    override fun areItemsTheSame(oldItem: ScadatelHistory, newItem: ScadatelHistory): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: ScadatelHistory, newItem: ScadatelHistory): Boolean {
        return when {
            oldItem.id != newItem.id -> false
            oldItem.modelName != newItem.modelName -> false
            oldItem.oldValue != newItem.oldValue -> false
            oldItem.newValue != newItem.newValue -> false
            oldItem.documentId != newItem.documentId -> false
            oldItem.fieldName != newItem.fieldName -> false
            else -> true
        }
    }
}