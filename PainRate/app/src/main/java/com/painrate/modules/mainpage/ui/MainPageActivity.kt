package com.painrate.modules.mainpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityMainPageBinding
import com.painrate.modules.capture.ui.CaptureActivity
import com.painrate.modules.mainpage.`data`.model.MainPageRowModel
import com.painrate.modules.mainpage.`data`.viewmodel.MainPageVM
import com.painrate.modules.menu.ui.MenuActivity
import com.painrate.modules.notification.ui.NotificationActivity
import com.painrate.modules.patients.ui.PatientsActivity
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit

class MainPageActivity : BaseActivity<ActivityMainPageBinding>(R.layout.activity_main_page) {
  private val viewModel: MainPageVM by viewModels<MainPageVM>()

  private lateinit var dashButton: LinearLayout
  private lateinit var camButton: LinearLayout
  private lateinit var patientsButton: LinearLayout
  private lateinit var notifButton: LinearLayout
  private lateinit var menuButton: LinearLayout



  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val mainPageAdapter = MainPageAdapter(viewModel.mainPageList.value?:mutableListOf())


    binding.recyclerMainPage.adapter = mainPageAdapter
    mainPageAdapter.setOnItemClickListener(
    object : MainPageAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : MainPageRowModel) {
        onClickRecyclerMainPage(view, position, item)
      }
    }
    )
    viewModel.mainPageList.observe(this) {
      mainPageAdapter.updateData(it)
    }
    binding.mainPageVM = viewModel
    setUpSearchViewGroupSeventeenListener()
  }

  override fun setUpClicks(): Unit {
    binding.linearMenu.setOnClickListener {
      val destIntent = MenuActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtLanguageEight.setOnClickListener {
      val destIntent = PatientsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtLanguageFour.setOnClickListener {
      val destIntent = PatientsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtLanguageOne.setOnClickListener {
      val destIntent = PatientsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtLanguageNine.setOnClickListener {
      val destIntent = PatientsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtLanguageSeven.setOnClickListener {
      val destIntent = PatientsActivity.getIntent(this, null)
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

  fun onClickRecyclerMainPage(
    view: View,
    position: Int,
    item: MainPageRowModel
  ): Unit {
    when(view.id) {
    }
  }

  private fun setUpSearchViewGroupSeventeenListener(): Unit {
    binding.searchViewGroupSeventeen.setOnQueryTextListener(object :
    SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(p0 : String) : Boolean {
        // Performs search when user hit
        // the search button on the keyboard
        return false
      }
      override fun onQueryTextChange(p0 : String) : Boolean {
        // Start filtering the list as user
        // start entering the characters
        return false
      }
      })
    }

    companion object {
      const val TAG: String = "MAIN_PAGE_ACTIVITY"


      fun getIntent(context: Context, bundle: Bundle?): Intent {
        val destIntent = Intent(context, MainPageActivity::class.java)
        destIntent.putExtra("bundle", bundle)
        return destIntent
      }
    }
  }
