import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appsanta.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ScheduleAppointmentActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_appointment)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val buttonSchedule: Button = findViewById(R.id.buttonSchedule)
        val editTextDate: EditText = findViewById(R.id.editTextDate)
        val editTextTime: EditText = findViewById(R.id.editTextTime)

        buttonSchedule.setOnClickListener {
            val date = editTextDate.text.toString()
            val time = editTextTime.text.toString()
            val patientId = auth.currentUser?.uid

            val appointment = hashMapOf(
                "date" to date,
                "time" to time,
                "patientId" to patientId
            )

            db.collection("appointments").add(appointment)
                .addOnSuccessListener {
                    Toast.makeText(this, "Consulta agendada com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Erro ao agendar consulta", Toast.LENGTH_SHORT).show()
                }
        }
    }
}