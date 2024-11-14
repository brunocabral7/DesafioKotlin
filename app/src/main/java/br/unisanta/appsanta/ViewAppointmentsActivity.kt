import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appsanta.R
import com.google.firebase.firestore.FirebaseFirestore

class ViewAppointmentsActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_appointments)

        db = FirebaseFirestore.getInstance()
        val appointmentContainer: LinearLayout = findViewById(R.id.appointmentContainer)

        db.collection("appointments").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val date = document.getString("date")
                    val time = document.getString("time")
                    val appointmentView = TextView(this)
                    appointmentView.text = "Data: $date, Horário: $time"
                    appointmentContainer.addView(appointmentView)
                }
            }
    }
}