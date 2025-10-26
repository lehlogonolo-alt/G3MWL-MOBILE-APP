

package student.projects.trialmanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PatientsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PatientAdapter
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var searchView: SearchView
    private val patientList = mutableListOf<PatientReport>()
    private val filteredList = mutableListOf<PatientReport>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patients)

        recyclerView = findViewById(R.id.recyclerPatients)
        topAppBar = findViewById(R.id.topAppBar)
        searchView = findViewById(R.id.searchViewPatients)
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAddRecordActivity)

        // 🔙 Back button -> Always return to Dashboard
        topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        // 🧾 Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PatientAdapter(filteredList) { selectedPatient ->
            val intent = Intent(this, ReportDetailActivity::class.java)
            intent.putExtra("patientReport", selectedPatient)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // ➕ Add new record
        fabAdd.setOnClickListener {
            val intent = Intent(this, AddRecordActivity::class.java)
            startActivity(intent)
        }

        // Load patients initially
        loadPatients()

        // 🔍 Search listener
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterPatients(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterPatients(newText)
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        loadPatients()
    }

    private fun loadPatients() {
        patientList.clear()

        // 🟦 Load actual records saved from AddRecordActivity
        val records = RecordsStore.records
        records.forEachIndexed { index, rec ->
            patientList.add(
                PatientReport(
                    id = (index + 1).toString(),
                    trialName = rec.trialName,
                    trialPhase = rec.trialPhase,
                    patientName = rec.patientName,
                    patientAge = rec.patientAge,
                    patientGender = rec.patientGender,
                    enrollmentDate = rec.enrollmentDate,
                    dosage = rec.dosage,
                    status = rec.status,
                    sideEffect = rec.sideEffect,
                    severity = rec.severity,
                    notes = rec.notes
                )
            )
        }

        // 🧪 Add 5 sample demo patients for display/testing
        patientList.addAll(
            listOf(
                PatientReport(
                    id = "P001",
                    trialName = "COVID-19 Vaccine Study",
                    trialPhase = "Phase III",
                    patientName = "Alice Mokoena",
                    patientAge = "28",
                    patientGender = "Female",
                    enrollmentDate = "2025-01-10",
                    dosage = "50mg",
                    status = "Active",
                    sideEffect = "Mild fever",
                    severity = "Low",
                    notes = "Responding well to treatment"
                ),
                PatientReport(
                    id = "P002",
                    trialName = "Cancer Drug Trial",
                    trialPhase = "Phase II",
                    patientName = "John Dlamini",
                    patientAge = "45",
                    patientGender = "Male",
                    enrollmentDate = "2025-02-14",
                    dosage = "75mg",
                    status = "Completed",
                    sideEffect = "Nausea",
                    severity = "Moderate",
                    notes = "Treatment completed successfully"
                ),
                PatientReport(
                    id = "P003",
                    trialName = "Diabetes Control Trial",
                    trialPhase = "Phase I",
                    patientName = "Lerato Nkosi",
                    patientAge = "34",
                    patientGender = "Female",
                    enrollmentDate = "2025-03-20",
                    dosage = "30mg",
                    status = "Pending",
                    sideEffect = "None",
                    severity = "None",
                    notes = "Awaiting next phase approval"
                ),
                PatientReport(
                    id = "P004",
                    trialName = "Blood Pressure Research",
                    trialPhase = "Phase III",
                    patientName = "Peter van Wyk",
                    patientAge = "52",
                    patientGender = "Male",
                    enrollmentDate = "2025-05-08",
                    dosage = "40mg",
                    status = "Active",
                    sideEffect = "Dizziness",
                    severity = "Low",
                    notes = "Stable readings over past week"
                ),
                PatientReport(
                    id = "P005",
                    trialName = "Asthma Treatment Study",
                    trialPhase = "Phase II",
                    patientName = "Nomsa Khumalo",
                    patientAge = "39",
                    patientGender = "Female",
                    enrollmentDate = "2025-06-15",
                    dosage = "60mg",
                    status = "Completed",
                    sideEffect = "Mild cough",
                    severity = "Low",
                    notes = "Positive long-term response"
                )
            )
        )

        // 🔍 Refresh filtered list to apply search immediately
        filterPatients(searchView.query.toString())
    }


    private fun filterPatients(query: String?) {
        filteredList.clear()
        if (query.isNullOrEmpty()) {
            filteredList.addAll(patientList)
        } else {
            val lowerQuery = query.lowercase()
            filteredList.addAll(patientList.filter {
                it.patientName.lowercase().contains(lowerQuery)
            })
        }
        adapter.notifyDataSetChanged()
    }
}

