package com.painrate.modules.mainpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.painrate.modules.mainpage.`data`.model.MainPageModel
import com.painrate.modules.mainpage.`data`.model.MainPageRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class MainPageVM : ViewModel(), KoinComponent {
  val mainPageModel: MutableLiveData<MainPageModel> = MutableLiveData(MainPageModel())

  var navArguments: Bundle? = null

  val mainPageList: MutableLiveData<MutableList<MainPageRowModel>> =
      MutableLiveData(mutableListOf())
}
