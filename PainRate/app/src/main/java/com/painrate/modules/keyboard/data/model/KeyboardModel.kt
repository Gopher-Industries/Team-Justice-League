package com.painrate.modules.keyboard.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class KeyboardModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSuggestion: String? = MyApp.getInstance().resources.getString(R.string.lbl_i)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSuggestionOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_i_m)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSuggestionTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_we)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_space)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etKeyThreeValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etReturnkeyValue: String? = null
)
