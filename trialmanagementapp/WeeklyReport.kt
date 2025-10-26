package student.projects.trialmanagementapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeeklyReport(
    val weekNumber: Int,
    val weight: String,
    val dosage: String,
    val weightLost: String,
    val sideEffects: String,
    val notes: String
) : Parcelable
