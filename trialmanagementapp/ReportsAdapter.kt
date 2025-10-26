package student.projects.trialmanagementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import student.projects.trialmanagementapp.R

class ReportsAdapter(
    private val reportsList: List<PatientReport>,
    private val onViewReport: (PatientReport) -> Unit
) : RecyclerView.Adapter<ReportsAdapter.ReportViewHolder>() {

    inner class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val btnView: Button = itemView.findViewById(R.id.btnViewReport)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_report, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val r = reportsList[position]
        holder.tvTitle.text = "${r.trialName} — Phase ${r.trialPhase}"

        holder.tvDescription.text = "Patient: ${r.patientName} | Age: ${r.patientAge} | Gender: ${r.patientGender}\n" +
                "Enrolled: ${r.enrollmentDate} | Dosage: ${r.dosage} | Status: ${r.status}\n" +
                "Side Effect: ${if (r.sideEffect.isBlank()) "-" else r.sideEffect} " +
                "(Severity: ${if (r.severity.isBlank()) "N/A" else r.severity})"

        holder.btnView.setOnClickListener { onViewReport(r) }
    }

    override fun getItemCount(): Int = reportsList.size
}


