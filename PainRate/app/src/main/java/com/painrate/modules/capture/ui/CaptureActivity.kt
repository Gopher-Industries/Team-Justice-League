package com.painrate.modules.capture.ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.example.PainRate.accessingnet.PostClass
import com.painrate.R
import com.painrate.appcomponents.base.BaseActivity
import com.painrate.databinding.ActivityCaptureBinding
import com.painrate.modules.capture.data.viewmodel.CaptureVM
import com.painrate.modules.mainpage.ui.MainPageActivity
import com.painrate.modules.menu.ui.MenuActivity
import com.painrate.modules.notification.ui.NotificationActivity
import com.painrate.modules.patients.ui.PatientsActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class CaptureActivity : BaseActivity<ActivityCaptureBinding>(R.layout.activity_capture) {
  private val viewModel: CaptureVM by viewModels<CaptureVM>()

  private lateinit var dashButton: LinearLayout
  private lateinit var camButton: LinearLayout
  private lateinit var patientsButton: LinearLayout
  private lateinit var notifButton: LinearLayout
  private lateinit var menuButton: LinearLayout

  private lateinit var btn_takephoto: View

  private lateinit var myImageView: ImageView

  private val PERMISSION_CODE = 1000
  private val IMAGE_CAPTURE_CODE = 1001

  private lateinit var painText: TextView

//  var vFilename: String = "example_target_)frame.png"
  var vFilename: String = ""

  val fileLocationString: String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
//  val fileLocationString: String = "res/drawable/"



  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.captureVM = viewModel

  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      val destIntent = MainPageActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
    }
//    binding.linearMenu.setOnClickListener {
//      val destIntent = MenuActivity.getIntent(this, null)
//      startActivity(destIntent)
//    }
    dashButton = findViewById(R.id.linearDashboard)
    dashButton.setOnClickListener{
      val destIntent = MainPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    camButton = findViewById(R.id.linearCapture)
    camButton.setOnClickListener{
      val destIntent = CaptureActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    patientsButton = findViewById(R.id.linearPatients)
    patientsButton.setOnClickListener{
      val destIntent = PatientsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    notifButton = findViewById(R.id.linearNotification)
    notifButton.setOnClickListener{
      val destIntent = NotificationActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    menuButton = findViewById(R.id.linearMenu)
    menuButton.setOnClickListener {
      val destIntent = MenuActivity.getIntent(this, null)
      startActivity(destIntent)
    }

    btn_takephoto = findViewById(R.id.viewButton)
    btn_takephoto.setOnClickListener {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        openCamera()
      } else {
        Toast.makeText(this, "Sorry you're version android is not support, Min Android 6.0 (Marsmallow)",
          Toast.LENGTH_LONG).show()
      }
    }
//    myImageView = findViewById(R.id.myImageView)
//    myImageView.setOnClickListener {
//
//      }
  }

  private fun openCamera() {
    val values = ContentValues()
    values.put(MediaStore.Images.Media.TITLE, "New Picture")
    values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")

    //camera intent
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    // set filename
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    vFilename = "FOTO_" + timeStamp + ".jpg"



    // set direcory folder
    val file = File(fileLocationString, vFilename);
    val image_uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);

    Toast.makeText(this, image_uri.toString(),
      Toast.LENGTH_LONG).show()

    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
    startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    //called when user presses ALLOW or DENY from Permission Request Popup
    when(requestCode){
      PERMISSION_CODE -> {
        if (grantResults.size > 0 && grantResults[0] ==
          PackageManager.PERMISSION_GRANTED){
          //permission from popup was granted
          openCamera()
        }
        else{
          //permission from popup was denied
          Toast.makeText(this, "Permission denied",
            Toast.LENGTH_LONG).show()
        }
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data);

//    Toast.makeText(this, "The onActivityResult function has started",
//      Toast.LENGTH_LONG).show()

    if (resultCode == RESULT_OK) {

      Toast.makeText(this, "Result Ok",
        Toast.LENGTH_LONG).show()

      //File object of camera image
      val file = File(fileLocationString, vFilename);
      Toast.makeText(this, file.toString(),
        Toast.LENGTH_LONG).show()

      //Uri of camera image
      val uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file)
//      myImageView.setImageURI(uri)

//      val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
//      myImageView.setImageBitmap(bitmap)

//      val destIntent = MainPageActivity.getIntent(this, null)
//      startActivity(destIntent)
//      finish()


      val takenImage = BitmapFactory.decodeFile(file.absolutePath)
      val imageView = findViewById<ImageView>(R.id.myImageView)
      imageView.setImageBitmap(takenImage)

      val conn = PostClass()

      val result = conn.clientOkHttp(file)
      println(result)
      Toast.makeText(this, result,
      Toast.LENGTH_LONG).show()

      Log.i("PAIN RESULTS", result)

      // This is a test line to show example of result

      val resultTest = 3.2


      painText = findViewById(R.id.textView)
      painText.text = ("Pain Rate: " + resultTest)
//      painText.text = ("Pain Rate: " + result)
      painText.visibility = View.VISIBLE

    }


    else{
      Toast.makeText(this, "Results NOT OK",
        Toast.LENGTH_LONG).show()

    }
    }


  companion object {
    const val TAG: String = "CAPTURE_ACTIVITY"
    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CaptureActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
