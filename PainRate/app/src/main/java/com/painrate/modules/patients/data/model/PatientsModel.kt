package com.painrate.modules.patients.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class PatientsModel(
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
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMrunknow: String? = MyApp.getInstance().resources.getString(R.string.lbl_mr_unknow)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSearchOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_search)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPatientlist: String? = MyApp.getInstance().resources.getString(R.string.lbl_patient_list)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_kiran_smith)
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
  var txtMoreinfo: String? = MyApp.getInstance().resources.getString(R.string.lbl_more_info)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_tom_papam)
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
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_rick_inzelo)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEppingcarecen: String? =
      MyApp.getInstance().resources.getString(R.string.msg_epping_care_cen)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMoreinfoOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_more_info)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMoreinfoTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_more_info)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageThree: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_marie_macarthy)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRedcrossagedc: String? =
      MyApp.getInstance().resources.getString(R.string.msg_redcross_aged_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMoreinfoThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_more_info)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_more)

)
