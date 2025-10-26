package student.projects.trialmanagementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SideEffectAdapter(private val sideEffects: List<SideEffect>) :
    RecyclerView.Adapter<SideEffectAdapter.SideEffectViewHolder>() {

    class SideEffectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPatientName: TextView = itemView.findViewById(R.id.tvPatientName)
        val tvName: TextView = itemView.findViewById(R.id.tvSideEffectName)
        val tvSeverity: TextView = itemView.findViewById(R.id.tvSideEffectSeverity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideEffectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_side_effect, parent, false)
        return SideEffectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SideEffectViewHolder, position: Int) {
        val sideEffect = sideEffects[position]
        holder.tvPatientName.text = sideEffect.patientName
        holder.tvName.text = sideEffect.name
        holder.tvSeverity.text = sideEffect.severity
    }

    override fun getItemCount() = sideEffects.size
}

