package com.painrate.modules.mainpage.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class MainPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWelcome: String? = MyApp.getInstance().resources.getString(R.string.lbl_welcome)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMrunknown: String? = MyApp.getInstance().resources.getString(R.string.lbl_mr_unknown)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTemperature: String? = MyApp.getInstance().resources.getString(R.string.lbl_26_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtBurwoodVIC: String? = MyApp.getInstance().resources.getString(R.string.lbl_burwood_vic)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_upcoming_consul)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_more)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_kiran_smith)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_tom_papam)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTarneitvalley: String? =
      MyApp.getInstance().resources.getString(R.string.msg_tarneit_valley)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMoorabinagedc: String? =
      MyApp.getInstance().resources.getString(R.string.msg_moorabin_aged_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAgedcarelocat: String? =
      MyApp.getInstance().resources.getString(R.string.msg_aged_care_locat)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_more)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDandenongcare: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dandenong_care)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageFive: String? =
      MyApp.getInstance().resources.getString(R.string.msg_redcross_care_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageSix: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_epping_age_care)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatients: String? = MyApp.getInstance().resources.getString(R.string.lbl_patients)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageSeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_more)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientName: String? = MyApp.getInstance().resources.getString(R.string.lbl_heng_yun_chou)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameOne: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_stephan_ewart)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_lina_hoek)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameThree: String? =
      MyApp.getInstance().resources.getString(R.string.msg_zekeriya_villan)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameFour: String? =
      MyApp.getInstance().resources.getString(R.string.msg_priscilla_spezi)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameFive: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_radoslaw_brogan)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLocationOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_location)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageEight: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_more)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDandenongcareOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dandenong_care)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDuration: String? = MyApp.getInstance().resources.getString(R.string.msg_open_hour_9_00)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDandenongcareTwo: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dandenong_care)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDurationOne: String? = MyApp.getInstance().resources.getString(R.string.msg_open_hour_9_00)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientsOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_patients)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageNine: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_more)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameSix: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_heng_yun_chou)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameSeven: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_stephan_ewart)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameEight: String? = MyApp.getInstance().resources.getString(R.string.lbl_lina_hoek)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameNine: String? =
      MyApp.getInstance().resources.getString(R.string.msg_zekeriya_villan)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameTen: String? =
      MyApp.getInstance().resources.getString(R.string.msg_priscilla_spezi)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientNameEleven: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_radoslaw_brogan)
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
  var txtPatientsTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_patients)
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
