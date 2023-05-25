package com.painrate.modules.patients.ui

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityPatientsBinding
import com.painrate.modules.capture.ui.CaptureActivity
import com.painrate.modules.keyboard.ui.KeyboardActivity
import com.painrate.modules.mainpage.ui.MainPageActivity
import com.painrate.modules.menu.ui.MenuActivity
import com.painrate.modules.notification.ui.NotificationActivity
import com.painrate.modules.patients.data.viewmodel.PatientsVM

class PatientsActivity : BaseActivity<ActivityPatientsBinding>(R.layout.activity_patients) {

  private val viewModel: PatientsVM by viewModels<PatientsVM>()
  private lateinit var recyclerView: RecyclerView
  lateinit var db:PatientDatabaseKt
  private lateinit var newArray: ArrayList<Datalist>
  private lateinit var adapter: CustomAdapter

  private lateinit var addPatientText: TextView

  private lateinit var dashButton: LinearLayout
  private lateinit var camButton: LinearLayout
  private lateinit var patientsButton: LinearLayout
  private lateinit var notifButton: LinearLayout
  private lateinit var menuButton: LinearLayout

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.patientsVM = viewModel

    addPatientText = findViewById(R.id.txtLanguageFour3)
    recyclerView = findViewById(R.id.patientRecycler)

    db = PatientDatabaseKt(this)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.setHasFixedSize(true)
    displayuser()

  }

  override fun setUpClicks(): Unit {
    binding.linearRowsearch.setOnClickListener {
      val destIntent = KeyboardActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearMenu.setOnClickListener {
      val destIntent = MenuActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    addPatientText = findViewById(R.id.txtLanguageFour3)
    addPatientText.text = "Add Patient"
    addPatientText.setOnClickListener(){
      val destIntent = AddPatient.getIntent(this, null)
      startActivity(destIntent)
    }

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


  }
  private fun displayuser(){
    var newcursor: Cursor? = db!!.gettext()
    newArray = ArrayList<Datalist>()
    while(newcursor!!.moveToNext()){
      val uId = newcursor.getString(0)
      val uname = newcursor.getString(1)
      val uphone = newcursor.getString(2)
      val unotes = newcursor.getString(3)
      val ulocation = newcursor.getString(4)
      val udob = newcursor.getString(5)

      newArray.add(Datalist(uname, uphone, unotes, ulocation, udob))
    }
    adapter = CustomAdapter(newArray)
    recyclerView.adapter = adapter
    adapter.onItemClickListener(object : CustomAdapter.onItemClickListener{
      override fun onItemClick(position: Int) {
        val intent = Intent(this@PatientsActivity, PatientView::class.java)
        intent.putExtra("name", newArray[position].name)
        intent.putExtra("phone", newArray[position].phone)
        intent.putExtra("notes", newArray[position].notes)
        intent.putExtra("location", newArray[position].location)
        intent.putExtra("dob", newArray[position].dob)
        startActivity(intent)
      }

    })
  }


  companion object {
    const val TAG: String = "PATIENTS_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PatientsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
