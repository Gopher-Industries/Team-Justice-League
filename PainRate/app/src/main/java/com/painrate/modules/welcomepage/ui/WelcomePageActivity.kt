package com.painrate.modules.welcomepage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityWelcomePageBinding
import com.painrate.modules.mainpage.ui.MainPageActivity
import com.painrate.modules.welcomepage.data.viewmodel.WelcomePageVM

class WelcomePageActivity : BaseActivity<ActivityWelcomePageBinding>(R.layout.activity_welcome_page)
    {
  private val viewModel: WelcomePageVM by viewModels<WelcomePageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.welcomePageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnStart.setOnClickListener {
      val destIntent = MainPageActivity.getIntent(this, null)
      startActivity(destIntent)
      this@WelcomePageActivity.finish()
    }
  }

  companion object {
    const val TAG: String = "WELCOME_PAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, WelcomePageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
