package com.capstone.metricapp.core.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.capstone.metricapp.core.domain.model.KeypointHistory

class ScadatelHistoryDiffUtil : DiffUtil.ItemCallback<KeypointHistory>() {
    override fun areItemsTheSame(oldItem: KeypointHistory, newItem: KeypointHistory): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: KeypointHistory, newItem: KeypointHistory): Boolean {
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