package com.painrate.modules.emergency.ui

import androidx.activity.viewModels
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityEmergencyBinding
import com.painrate.modules.emergency.`data`.viewmodel.EmergencyVM
import com.painrate.modules.mainpage.ui.MainPageActivity
import kotlin.String
import kotlin.Unit

class EmergencyActivity : BaseActivity<ActivityEmergencyBinding>(R.layout.activity_emergency) {
  private val viewModel: EmergencyVM by viewModels<EmergencyVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.emergencyVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtBacktomainpag.setOnClickListener {
      val destIntent = MainPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      val destIntent = MainPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "EMERGENCY_ACTIVITY"

  }
}
