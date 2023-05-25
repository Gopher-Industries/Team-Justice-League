package com.painrate.modules.welcomepage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.painrate.modules.welcomepage.`data`.model.WelcomePageModel
import org.koin.core.KoinComponent

class WelcomePageVM : ViewModel(), KoinComponent {
  val welcomePageModel: MutableLiveData<WelcomePageModel> = MutableLiveData(WelcomePageModel())

  var navArguments: Bundle? = null
}
