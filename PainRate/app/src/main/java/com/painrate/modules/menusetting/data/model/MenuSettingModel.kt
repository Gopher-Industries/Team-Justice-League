package com.painrate.modules.menusetting.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class MenuSettingModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtNotificationSe: String? =
      MyApp.getInstance().resources.getString(R.string.msg_notification_se)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGeneralSetting: String? =
      MyApp.getInstance().resources.getString(R.string.msg_general_setting)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMyAddresses: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_addresses)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAccountSecurit: String? =
      MyApp.getInstance().resources.getString(R.string.msg_account_securit)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPrivacySetting: String? =
      MyApp.getInstance().resources.getString(R.string.msg_privacy_setting)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHelpCenter: String? = MyApp.getInstance().resources.getString(R.string.lbl_help_center)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRateOurApp: String? = MyApp.getInstance().resources.getString(R.string.lbl_rate_our_app)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPersonalInform: String? =
      MyApp.getInstance().resources.getString(R.string.msg_personal_inform)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCooperationLis: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cooperation_lis)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAboutUs: String? = MyApp.getInstance().resources.getString(R.string.lbl_about_us)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTeenMode: String? = MyApp.getInstance().resources.getString(R.string.lbl_teen_mode)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDarkMode: String? = MyApp.getInstance().resources.getString(R.string.lbl_dark_mode)
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
