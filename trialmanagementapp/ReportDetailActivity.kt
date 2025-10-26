package student.projects.trialmanagementapp

import android.os.Bundle
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import student.projects.trialmanagementapp.databinding.ActivityReportDetailBinding

class ReportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailTopAppBar.title = "Patient Details"
        binding.detailTopAppBar.setNavigationOnClickListener { onBackPressed() }

        val patientReport = intent.getParcelableExtra<PatientReport>("patientReport")
        patientReport?.let {
            binding.tvPatientName.text = "Name: ${it.patientName}"
            binding.tvPatientAge.text = "Age: ${it.patientAge}"
            binding.tvPatientGender.text = "Gender: ${it.patientGender}"
            binding.tvTreatmentStart.text = "Treatment Start: ${it.enrollmentDate}"
            binding.tvStartingWeight.text = "Starting Weight: ${it.dosage}"
        }

        binding.btnAddReport.setOnClickListener { showAddReportDialog() }
    }

    private fun showAddReportDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_report, null)

        val etWeight = dialogView.findViewById<EditText>(R.id.etCurrentWeight)
        val etDosage = dialogView.findViewById<EditText>(R.id.etDosage)
        val etWeightLost = dialogView.findViewById<EditText>(R.id.etWeightLost)
        val etSideEffects = dialogView.findViewById<EditText>(R.id.etSideEffects)
        val etNotes = dialogView.findViewById<EditText>(R.id.etNotes)

        MaterialAlertDialogBuilder(this)
            .setTitle("New Report")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val weight = etWeight.text.toString()
                val dosage = etDosage.text.toString()
                val weightLost = etWeightLost.text.toString()
                val sideEffects = etSideEffects.text.toString()
                val notes = etNotes.text.toString()

                val weekNumber = binding.tableWeeklyReports.childCount
                val newReport = WeeklyReport(weekNumber, weight, dosage, weightLost, sideEffects, notes)

                addReportToTable(newReport)
                Toast.makeText(this, "Report saved", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun addReportToTable(report: WeeklyReport) {
        val table = binding.tableWeeklyReports
        val newRow = TableRow(this)
        val padding = 8

        newRow.addView(TextView(this).apply { text = report.weekNumber.toString(); setPadding(padding,padding,padding,padding) })
        newRow.addView(TextView(this).apply { text = report.weight; setPadding(padding,padding,padding,padding) })
        newRow.addView(TextView(this).apply { text = report.dosage; setPadding(padding,padding,padding,padding) })
        newRow.addView(TextView(this).apply { text = report.sideEffects; setPadding(padding,padding,padding,padding) })
        newRow.addView(TextView(this).apply { text = report.weightLost; setPadding(padding,padding,padding,padding) })
        newRow.addView(TextView(this).apply { text = report.notes; setPadding(padding,padding,padding,padding) })

        table.addView(newRow)
    }
}


