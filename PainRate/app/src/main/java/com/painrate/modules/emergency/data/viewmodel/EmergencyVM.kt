package com.painrate.modules.emergency.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.painrate.modules.emergency.`data`.model.EmergencyModel
import org.koin.core.KoinComponent

class EmergencyVM : ViewModel(), KoinComponent {
  val emergencyModel: MutableLiveData<EmergencyModel> = MutableLiveData(EmergencyModel())

  var navArguments: Bundle? = null
}
