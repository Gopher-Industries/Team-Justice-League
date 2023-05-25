package com.painrate.modules.keyboard.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityKeyboardBinding
import com.painrate.modules.keyboard.`data`.model.KeyboardRowModel
import com.painrate.modules.keyboard.`data`.viewmodel.KeyboardVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class KeyboardActivity : BaseActivity<ActivityKeyboardBinding>(R.layout.activity_keyboard) {
  private val viewModel: KeyboardVM by viewModels<KeyboardVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val keyboardAdapter = KeyboardAdapter(viewModel.keyboardList.value?:mutableListOf())
    binding.recyclerKeyboard.adapter = keyboardAdapter
    keyboardAdapter.setOnItemClickListener(
    object : KeyboardAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : KeyboardRowModel) {
        onClickRecyclerKeyboard(view, position, item)
      }
    }
    )
    viewModel.keyboardList.observe(this) {
      keyboardAdapter.updateData(it)
    }
    binding.keyboardVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerKeyboard(
    view: View,
    position: Int,
    item: KeyboardRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "KEYBOARD_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, KeyboardActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
