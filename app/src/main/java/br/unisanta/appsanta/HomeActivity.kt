import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appsanta.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

    class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val userId = auth.currentUser?.uid
        val buttonFunctionality: Button = findViewById(R.id.buttonFunctionality)

        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    val userType = document.getString("userType")
                    if (userType == "Paciente") {
                        buttonFunctionality.text = "Agendar Consulta"
                        buttonFunctionality.setOnClickListener {
                            startActivity(Intent(this, ScheduleAppointmentActivity::class.java))
                        }
                    } else if (userType == "Médico") {
                        buttonFunctionality.text = "Ver Consultas"
                        buttonFunctionality.setOnClickListener {
                            startActivity(Intent(this, ViewAppointmentsActivity::class.java))
                        }
                    }
                }
        }
    }
}
