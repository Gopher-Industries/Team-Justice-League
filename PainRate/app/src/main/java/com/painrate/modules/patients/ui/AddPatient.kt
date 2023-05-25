package com.painrate.modules.patients.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.painrate.R

class AddPatient : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var notes: EditText
    private lateinit var location: EditText
    private lateinit var dob: EditText
    private lateinit var addPat: Button

    private lateinit var db: PatientDatabaseKt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        name = findViewById(R.id.editTextName)
        phone = findViewById(R.id.editTextPhone)
        notes = findViewById(R.id.editTextNotes)
        location = findViewById(R.id.editTextLoc)
        dob = findViewById(R.id.editTextDob)

        addPat = findViewById(R.id.button3)

        db = PatientDatabaseKt(this)

        addPat.setOnClickListener(){
            val names = name.text.toString()
            val phones = phone.text.toString()
            val notes = notes.text.toString()
            val location = location.text.toString()
            val dob = dob.text.toString()

            val savedata = db.saveuserdata(names, phones, notes, location, dob)
            if(TextUtils.isEmpty(names) || TextUtils.isEmpty(phones)){
                Toast.makeText(this, "Add Data", Toast.LENGTH_SHORT).show()
            }
            else{
                if(savedata==true){
                    Toast.makeText(this, "Patient Saved", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Exist Contact", Toast.LENGTH_SHORT).show()
                }
            }

            val destIntent = PatientsActivity.getIntent(this, null)
            startActivity(destIntent)
        }

        
    }
    companion object {
        const val TAG: String = "ADDPATIENT_ACTIVITY"


        fun getIntent(context: Context, bundle: Bundle?): Intent {
            val destIntent = Intent(context, AddPatient::class.java)
            destIntent.putExtra("bundle", bundle)
            return destIntent
        }
    }
}
