package com.painrate.modules.menusetting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityMenuSettingBinding
import com.painrate.modules.menu.ui.MenuActivity
import com.painrate.modules.menusetting.`data`.viewmodel.MenuSettingVM
import kotlin.String
import kotlin.Unit

class MenuSettingActivity : BaseActivity<ActivityMenuSettingBinding>(R.layout.activity_menu_setting)
    {
  private val viewModel: MenuSettingVM by viewModels<MenuSettingVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.menuSettingVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnFinish.setOnClickListener {
      val destIntent = MenuActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "MENU_SETTING_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MenuSettingActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
