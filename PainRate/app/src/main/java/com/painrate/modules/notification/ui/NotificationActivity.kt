package com.painrate.modules.notification.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityNotificationBinding
import com.painrate.modules.capture.ui.CaptureActivity
import com.painrate.modules.chatbox.ui.ChatBoxActivity
import com.painrate.modules.mainpage.ui.MainPageActivity
import com.painrate.modules.menu.ui.MenuActivity
import com.painrate.modules.notification.`data`.viewmodel.NotificationVM
import com.painrate.modules.patients.ui.PatientsActivity
import kotlin.String
import kotlin.Unit

class NotificationActivity :
    BaseActivity<ActivityNotificationBinding>(R.layout.activity_notification) {
  private val viewModel: NotificationVM by viewModels<NotificationVM>()

  private lateinit var dashButton: LinearLayout
  private lateinit var camButton: LinearLayout
  private lateinit var patientsButton: LinearLayout
  private lateinit var notifButton: LinearLayout
  private lateinit var menuButton: LinearLayout

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.notificationVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtTwoTwo.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtDrMarthaOne.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageProfilePictureThree.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtDrStevenOne.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtTwoOne.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtPriceFive.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtDrKateOne.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtPriceThree.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearColumntwoOne.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtPriceFour.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearMenu.setOnClickListener {
      val destIntent = MenuActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearColumntwo.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtPrice.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtTwoThree.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageProfilePictureFour.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageProfilePictureFive.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtDrKate.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtDrSteven.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtPriceOne.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtPriceTwo.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageProfilePictureOne.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtDrMartha.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageProfilePicture.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtTwo.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageProfilePictureTwo.setOnClickListener {
      val destIntent = ChatBoxActivity.getIntent(this, null)
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

  companion object {
    const val TAG: String = "NOTIFICATION_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NotificationActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
