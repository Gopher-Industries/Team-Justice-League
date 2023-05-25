package com.painrate.modules.patients.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.painrate.R
import com.painrate.modules.capture.ui.CaptureActivity
import com.painrate.modules.mainpage.ui.MainPageActivity
import com.painrate.modules.menu.ui.MenuActivity
import com.painrate.modules.notification.ui.NotificationActivity

class PatientView : AppCompatActivity() {
    private lateinit var profilePicView: ImageView
    private lateinit var nameText: TextView
    private lateinit var phoneText: TextView
    private lateinit var notesText: TextView
    private lateinit var locationText: TextView
    private lateinit var dobText: TextView

    private lateinit var deleteButton: Button
    private lateinit var editButton: Button

    private lateinit var db: PatientDatabaseKt

    private lateinit var dashButton: LinearLayout
    private lateinit var camButton: LinearLayout
    private lateinit var patientsButton: LinearLayout
    private lateinit var notifButton: LinearLayout
    private lateinit var menuButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_view)

        profilePicView = findViewById(R.id.imageView4)
        nameText = findViewById(R.id.nameView)
        phoneText = findViewById(R.id.phoneView)
        notesText = findViewById(R.id.notesView)
        locationText = findViewById(R.id.locationView)
        dobText = findViewById(R.id.dobView)

        deleteButton = findViewById(R.id.deleteButton)
        editButton = findViewById(R.id.editButton)

        db = PatientDatabaseKt(this)

        nameText.setText(intent.getStringExtra("name"))
        phoneText.setText(intent.getStringExtra("phone"))
        notesText.setText(intent.getStringExtra("notes"))
        locationText.setText(intent.getStringExtra("location"))
        dobText.setText(intent.getStringExtra("dob"))

        dashButton = findViewById(R.id.linearDashboard)
        dashButton.setOnClickListener{
            val destIntent = MainPageActivity.getIntent(this, null)
            startActivity(destIntent)
        }
        camButton = findViewById(R.id.linearCapture)
        camButton.setOnClickListener{
            val destIntent = CaptureActivity.getIntent(this, null)
            startActivity(destIntent)
        }
        patientsButton = findViewById(R.id.linearPatients)
        patientsButton.setOnClickListener{
            val destIntent = PatientsActivity.getIntent(this, null)
            startActivity(destIntent)
        }
        notifButton = findViewById(R.id.linearNotification)
        notifButton.setOnClickListener{
            val destIntent = NotificationActivity.getIntent(this, null)
            startActivity(destIntent)
        }
        menuButton = findViewById(R.id.linearMenu)
        menuButton.setOnClickListener {
            val destIntent = MenuActivity.getIntent(this, null)
            startActivity(destIntent)
        }

        deleteButton.setOnClickListener{
            val names = nameText.text.toString()
            val deletedata = db.deleteuserdata(names)

            if(deletedata==true){

                Toast.makeText(this, "Patient Deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Patient NOT Deleted", Toast.LENGTH_SHORT).show()
            }
            val destIntent = PatientsActivity.getIntent(this, null)
            startActivity(destIntent)
        }
//TODO FIX THIS
        editButton.setOnClickListener{
            val names = nameText.text.toString()
            val phones = phoneText.text.toString()
            val notes = notesText.text.toString()
            val location = locationText.text.toString()
            val dob = dobText.text.toString()
            val deletedata = db.deleteuserdata(names)

            if(deletedata==true){

                Toast.makeText(this, "Patient Deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Patient NOT Deleted", Toast.LENGTH_SHORT).show()
            }
            val destIntent = PatientsActivity.getIntent(this, null)
            startActivity(destIntent)

        }
    }
}
