package com.painrate.modules.notification.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.painrate.modules.notification.`data`.model.NotificationModel
import org.koin.core.KoinComponent

class NotificationVM : ViewModel(), KoinComponent {
  val notificationModel: MutableLiveData<NotificationModel> = MutableLiveData(NotificationModel())

  var navArguments: Bundle? = null
}
