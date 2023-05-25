package com.painrate.modules.signuppage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivitySignUpPageBinding
import com.painrate.modules.keyboard.ui.KeyboardActivity
import com.painrate.modules.mainpage.ui.MainPageActivity
import com.painrate.modules.signuppage.`data`.viewmodel.SignUpPageVM
import kotlin.String
import kotlin.Unit

class SignUpPageActivity : BaseActivity<ActivitySignUpPageBinding>(R.layout.activity_sign_up_page) {
  private val viewModel: SignUpPageVM by viewModels<SignUpPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.signUpPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnPassword.setOnClickListener {
      val destIntent = KeyboardActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnUserNameuserNumber.setOnClickListener {
      val destIntent = KeyboardActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnComfirmPasswordOne.setOnClickListener {
      val destIntent = KeyboardActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnSignUp.setOnClickListener {
      val destIntent = MainPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SIGN_UP_PAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SignUpPageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
