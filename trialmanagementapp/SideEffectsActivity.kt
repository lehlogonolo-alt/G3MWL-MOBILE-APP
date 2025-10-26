package student.projects.trialmanagementapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SideEffectsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SideEffectAdapter
    private val sideEffectsList = mutableListOf<SideEffect>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_effects)

        // ✅ Top App Bar with working back button
        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)
        topAppBar.setNavigationOnClickListener {
            finish()
        }

        recyclerView = findViewById(R.id.recyclerSideEffects)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SideEffectAdapter(sideEffectsList)
        recyclerView.adapter = adapter

        // ✅ Add Side Effect Button
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAddSideEffect)
        fabAdd.setOnClickListener {
            if (!isFinishing) {
                AddSideEffectsDialog { name, severity ->
                    if (name.isNotBlank() && severity.isNotBlank()) {
                        sideEffectsList.add(SideEffect("John Doe", name, severity))
                        adapter.notifyDataSetChanged()
                        Toast.makeText(this, "Side effect added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                }.show(supportFragmentManager, "AddSideEffectDialog")
            }
        }

        loadSideEffects()
    }

    private fun loadSideEffects() {
        // Example demo data
        sideEffectsList.add(SideEffect("John Doe", "Nausea", "Mild"))
        sideEffectsList.add(SideEffect("Mary Smith", "Headache", "Moderate"))
        adapter.notifyDataSetChanged()
    }
}

// ✅ Updated Model to include PatientName
data class SideEffect(val patientName: String, val name: String, val severity: String)


