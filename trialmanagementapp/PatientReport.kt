package student.projects.trialmanagementapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class PatientReport(
    val id: String,
    val trialName: String,
    val trialPhase: String,
    val patientName: String,
    val patientAge: String,
    val patientGender: String,
    val enrollmentDate: String,
    val dosage: String,
    val status: String,
    val sideEffect: String,
    val severity: String,
    val notes: String,
    val weeklyReports: ArrayList<WeeklyReport> = arrayListOf()
) : Parcelable
