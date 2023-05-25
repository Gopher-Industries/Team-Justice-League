package com.painrate.modules.keyboard.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class KeyboardRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtKey: String? = MyApp.getInstance().resources.getString(R.string.lbl_q)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_w)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_e)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_r)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_t)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_y)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeySix: String? = MyApp.getInstance().resources.getString(R.string.lbl_u)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeySeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_i2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyEight: String? = MyApp.getInstance().resources.getString(R.string.lbl_o)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKeyNine: String? = MyApp.getInstance().resources.getString(R.string.lbl_p)

)
