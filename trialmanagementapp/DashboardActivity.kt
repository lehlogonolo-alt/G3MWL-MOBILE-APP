package student.projects.trialmanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import student.projects.trialmanagementapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var loggedEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Set up toolbar with menu
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(topAppBar)
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_account -> {
                    val intent = Intent(this, AccountActivity::class.java)
                    intent.putExtra("loggedEmail", loggedEmail)
                    startActivity(intent)
                    true
                }
                R.id.menu_about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                R.id.menu_contact -> {
                    startActivity(Intent(this, ContactActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // ✅ Retrieve email from LoginActivity
        loggedEmail = intent.getStringExtra("loggedEmail")



        // 🟦 Patients card
        binding.cardPatients.setOnClickListener {
            startActivity(Intent(this, PatientsActivity::class.java))
        }


        // 🟥 Side Effects card
        binding.cardSideEffects.setOnClickListener {
            startActivity(Intent(this, SideEffectsActivity::class.java))
        }
    }

    // 🔽 ADD THIS BELOW onCreate()
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }
}

