package com.capstone.metricapp.core.utils.datamapper

import com.capstone.metricapp.core.utils.constans.Divisions

object DivisionDataMapper {
    fun mapStringToDivision(divisionName: String): Divisions {
        return if (divisionName == Divisions.SCADATEL.divisionName) {
            Divisions.SCADATEL
        } else {
            Divisions.SCADATEL
        }
    }
}