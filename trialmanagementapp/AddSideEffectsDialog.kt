package student.projects.trialmanagementapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AddSideEffectsDialog(
    private val onAdd: (name: String, severity: String) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.dialog_add_side_effect, null)

        val etName: EditText = view.findViewById(R.id.etSideEffectName)
        val spSeverity: Spinner = view.findViewById(R.id.spSeverity)
        val btnCancel: Button = view.findViewById(R.id.btnCancel)
        val btnSave: Button = view.findViewById(R.id.btnSave)

        // Populate severity spinner
        val severities = listOf("Mild", "Moderate", "Severe")
        spSeverity.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, severities)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .create()

        btnCancel.setOnClickListener { dialog.dismiss() }
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val severity = spSeverity.selectedItem.toString()
            onAdd(name, severity)
            dialog.dismiss()
        }

        return dialog
    }
}


