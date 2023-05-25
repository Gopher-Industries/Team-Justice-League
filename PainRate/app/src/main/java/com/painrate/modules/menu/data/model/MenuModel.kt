package com.painrate.modules.menu.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class MenuModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtProfileSet: String? = MyApp.getInstance().resources.getString(R.string.lbl_profile_set)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtReplacethepic: String? =
      MyApp.getInstance().resources.getString(R.string.msg_replace_the_pic)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLocation: String? = MyApp.getInstance().resources.getString(R.string.lbl_location)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtBurwoodVICMel: String? =
      MyApp.getInstance().resources.getString(R.string.msg_burwood_vic_mel)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUserName: String? = MyApp.getInstance().resources.getString(R.string.lbl_user_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWeburl: String? = MyApp.getInstance().resources.getString(R.string.lbl_mr_unknow2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtIntroductionAb: String? =
      MyApp.getInstance().resources.getString(R.string.msg_introduction_ab)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtStartyourheal: String? =
      MyApp.getInstance().resources.getString(R.string.msg_start_your_heal)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSettings: String? = MyApp.getInstance().resources.getString(R.string.lbl_settings)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtContactcurrent: String? =
      MyApp.getInstance().resources.getString(R.string.msg_contact_current)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDashboard: String? = MyApp.getInstance().resources.getString(R.string.lbl_dashboard)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCapture: String? = MyApp.getInstance().resources.getString(R.string.lbl_capture)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatients: String? = MyApp.getInstance().resources.getString(R.string.lbl_patients)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNotificationOne: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_notification)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMenuOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_menu)

)
