package com.painrate.modules.keyboard.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.painrate.modules.keyboard.`data`.model.KeyboardModel
import com.painrate.modules.keyboard.`data`.model.KeyboardRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class KeyboardVM : ViewModel(), KoinComponent {
  val keyboardModel: MutableLiveData<KeyboardModel> = MutableLiveData(KeyboardModel())

  var navArguments: Bundle? = null

  val keyboardList: MutableLiveData<MutableList<KeyboardRowModel>> =
      MutableLiveData(mutableListOf())
}
