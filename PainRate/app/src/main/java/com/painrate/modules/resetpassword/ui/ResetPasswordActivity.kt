package com.painrate.modules.resetpassword.ui

import androidx.activity.viewModels
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityResetPasswordBinding
import com.painrate.modules.keyboard.ui.KeyboardActivity
import com.painrate.modules.mainpage.ui.MainPageActivity
import com.painrate.modules.resetpassword.`data`.viewmodel.ResetPasswordVM
import kotlin.String
import kotlin.Unit

class ResetPasswordActivity :
    BaseActivity<ActivityResetPasswordBinding>(R.layout.activity_reset_password) {
  private val viewModel: ResetPasswordVM by viewModels<ResetPasswordVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.resetPasswordVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnUserNameuserNumber.setOnClickListener {
      val destIntent = KeyboardActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnResetPassword.setOnClickListener {
      val destIntent = KeyboardActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnComfirmPasswordOne.setOnClickListener {
      val destIntent = KeyboardActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnComfirm.setOnClickListener {
      val destIntent = MainPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "RESET_PASSWORD_ACTIVITY"

  }
}
