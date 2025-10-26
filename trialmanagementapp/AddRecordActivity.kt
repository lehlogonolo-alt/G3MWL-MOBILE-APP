package student.projects.trialmanagementapp

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class AddRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_record)

        val toolbar: MaterialToolbar? = findViewById(R.id.topAppBar)
        toolbar?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_add_record)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Input fields
        val etPatientName = findViewById<EditText>(R.id.etPatientName)
        val etPatientAge = findViewById<EditText>(R.id.etPatientAge)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val etTrialName = findViewById<EditText>(R.id.etTrialName)
        val etTrialPhase = findViewById<EditText>(R.id.etTrialPhase)
        val etEnrollmentDate = findViewById<EditText>(R.id.etEnrollmentDate)
        val etDosage = findViewById<EditText>(R.id.etDosage)
        val etStartingWeight = findViewById<EditText>(R.id.etStartingWeight)
        val spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)
        val etSideEffect = findViewById<EditText>(R.id.etSideEffect)
        val spinnerSeverity = findViewById<Spinner>(R.id.spinnerSeverity)
        val etSideEffectDate = findViewById<EditText>(R.id.etSideEffectDate)
        val etNotes = findViewById<EditText>(R.id.etNotes)
        val btnSaveRecord = findViewById<Button>(R.id.btnSaveRecord)
        val btnBack = findViewById<Button>(R.id.btnBack) // ✅ Added reference

        // Setup dropdowns
        spinnerGender.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("M", "F", "Other"))
        spinnerStatus.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("Active", "Inactive"))
        spinnerSeverity.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("1", "2", "3", "4", "5"))

        // ✅ Save Record Button Logic
        btnSaveRecord.setOnClickListener {
            val record = Record(
                patientName = etPatientName.text.toString().trim(),
                patientAge = etPatientAge.text.toString().trim(),
                patientGender = spinnerGender.selectedItem.toString(),
                trialName = etTrialName.text.toString().trim(),
                trialPhase = etTrialPhase.text.toString().trim(),
                enrollmentDate = etEnrollmentDate.text.toString().trim(),
                dosage = etDosage.text.toString().trim(),
                startingWeight = etStartingWeight.text.toString().trim(),
                status = spinnerStatus.selectedItem.toString(),
                sideEffect = etSideEffect.text.toString().trim(),
                severity = spinnerSeverity.selectedItem.toString(),
                sideEffectDate = etSideEffectDate.text.toString().trim(),
                notes = etNotes.text.toString().trim()  
            )

            // Validation
            if (record.patientName.isEmpty() || record.patientAge.isEmpty() ||
                record.trialName.isEmpty() || record.trialPhase.isEmpty() ||
                record.enrollmentDate.isEmpty() || record.dosage.isEmpty() ||
                record.startingWeight.isEmpty()
            ) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save record
            RecordsStore.records.add(record)
            Toast.makeText(this, "Record saved for ${record.patientName}", Toast.LENGTH_SHORT).show()

            // Clear fields
            etPatientName.text.clear()
            etPatientAge.text.clear()
            etTrialName.text.clear()
            etTrialPhase.text.clear()
            etEnrollmentDate.text.clear()
            etDosage.text.clear()
            etStartingWeight.text.clear()
            etSideEffect.text.clear()
            etSideEffectDate.text.clear()
            etNotes.text.clear()
            spinnerGender.setSelection(0)
            spinnerStatus.setSelection(0)
            spinnerSeverity.setSelection(0)
        }


        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}








