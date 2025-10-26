package student.projects.trialmanagementapp

// Data class for a trial record
data class Record(
    val patientName: String,
    val patientAge: String,
    val patientGender: String,
    val trialName: String,
    val trialPhase: String,
    val enrollmentDate: String,
    val dosage: String,
    val startingWeight: String,
    val status: String,
    val sideEffect: String,
    val severity: String,
    val sideEffectDate: String,
    val notes: String
)

// Singleton object to store records temporarily
object RecordsStore {
    val records = mutableListOf<Record>()
}
