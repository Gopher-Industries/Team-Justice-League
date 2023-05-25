package com.painrate.modules.patients.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.painrate.modules.patients.`data`.model.PatientsModel
import org.koin.core.KoinComponent

class PatientsVM : ViewModel(), KoinComponent {
  val patientsModel: MutableLiveData<PatientsModel> = MutableLiveData(PatientsModel())

  var navArguments: Bundle? = null
}
