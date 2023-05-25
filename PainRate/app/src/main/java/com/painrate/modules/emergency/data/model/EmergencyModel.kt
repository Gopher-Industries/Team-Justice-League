package com.painrate.modules.emergency.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class EmergencyModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtBacktomainpag: String? =
      MyApp.getInstance().resources.getString(R.string.msg_back_to_mainpag)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_warming)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMakesureyoua: String? =
      MyApp.getInstance().resources.getString(R.string.msg_make_sure_you_a)

)
