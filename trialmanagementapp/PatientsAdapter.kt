package student.projects.trialmanagementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PatientAdapter(
    private val patients: List<PatientReport>,
    private val onViewReportClick: (PatientReport) -> Unit
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvPatientName)
        val tvTrial: TextView = itemView.findViewById(R.id.tvPatientTrial)
        val tvStatus: TextView = itemView.findViewById(R.id.tvPatientStatus)
        val btnViewReport: Button = itemView.findViewById(R.id.btnViewReport)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patients[position]
        holder.tvName.text = patient.patientName
        holder.tvTrial.text = patient.trialName
        holder.tvStatus.text = patient.status

        holder.btnViewReport.setOnClickListener {
            onViewReportClick(patient)
        }
    }

    override fun getItemCount(): Int = patients.size
}
