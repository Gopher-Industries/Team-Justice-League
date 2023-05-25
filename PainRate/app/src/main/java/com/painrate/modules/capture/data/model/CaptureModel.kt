package com.painrate.modules.capture.`data`.model

import com.painrate.R
import com.painrate.appcomponents.di.MyApp
import kotlin.String

data class CaptureModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtVideo: String? = MyApp.getInstance().resources.getString(R.string.lbl_video)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhoto: String? = MyApp.getInstance().resources.getString(R.string.lbl_photo)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtVoice: String? = MyApp.getInstance().resources.getString(R.string.lbl_voice)
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
