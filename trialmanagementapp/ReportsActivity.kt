package student.projects.trialmanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import student.projects.trialmanagementapp.R
import student.projects.trialmanagementapp.R.layout.activity_reports

class ReportsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReportsAdapter
    private val reportsList = mutableListOf<PatientReport>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_reports)

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar.setNavigationOnClickListener { finish() }

        recyclerView = findViewById(R.id.rvReports)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ReportsAdapter(reportsList) { report ->
            // On "View Report" clicked -> open detail screen
            val i = Intent(this, ReportDetailActivity::class.java)
            i.putExtra("report", report)
            startActivity(i)
        }
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        // Refresh the reports list every time the activity comes to the foreground
        recordsToReports()
        adapter.notifyDataSetChanged()
    }

    private fun recordsToReports() {
        // Defensive: if RecordsStore not available, add placeholder sample
        try {
            val records = RecordsStore.records
            reportsList.clear()
            records.forEachIndexed { index, rec ->
                // Map Record to PatientReport
                reportsList.add(
                    PatientReport(
                        id = (index + 1).toString(),
                        trialName = rec.trialName ?: "",
                        trialPhase = rec.trialPhase ?: "",
                        patientName = rec.patientName ?: "",
                        patientAge = rec.patientAge ?: "",
                        patientGender = rec.patientGender ?: "",
                        enrollmentDate = rec.enrollmentDate ?: "",
                        dosage = rec.dosage ?: "",
                        status = rec.status ?: "",
                        sideEffect = rec.sideEffect ?: "",
                        severity = rec.severity ?: "",
                        notes = rec.notes ?: ""
                    )
                )
            }
        } catch (ex: Exception) {
            // fallback sample data
            reportsList.clear()
            reportsList.add(
                PatientReport(
                    id = "1",
                    trialName = "Sample Trial",
                    trialPhase = "2",
                    patientName = "John Doe",
                    patientAge = "34",
                    patientGender = "Male",
                    enrollmentDate = "2025-01-05",
                    dosage = "50mg",
                    status = "Active",
                    sideEffect = "Nausea",
                    severity = "Mild",
                    notes = "No additional notes"
                )
            )
        }
    }
}








