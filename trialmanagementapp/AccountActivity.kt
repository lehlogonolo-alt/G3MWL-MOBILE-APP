package student.projects.trialmanagementapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSave = findViewById<Button>(R.id.btnSaveAccount)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Get email from Intent if passed
        val loggedEmail = intent.getStringExtra("loggedEmail") ?: ""
        etEmail.setText(loggedEmail)  // show email used to login

        btnSave.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Account info saved (mock)", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}



