package com.example.PainRate

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.example.PainRate.PatientInfo.Patient
import com.example.PainRate.painlevel.painData
import com.example.PainRate.painlevel.painLevel
import com.example.PainRate.databinding.ActivityResultsBinding
import java.util.*


class Results : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    private var set = ConstraintSet()
    private var reset = ConstraintSet()

    companion object{
        const val RE = "Result"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup visibility variables
        set.clone(binding.root)
        reset.clone(binding.root)

        // Set patient name
        val patient = Patient("Kate", "Female", "21 March 1944")

        setPatientInfo(patient)

        // result receive
        val painResult:painData
        if (intent.extras != null){
            painResult = intent?.getSerializableExtra(RE) as painData
        }else{
            painResult = painLevel().loadPainLevel()
        }

        setPainResult(painResult)
        if (painResult.RPL > 0){
            setArrowPlace(painResult.RPL.toString())
        } else {
            setNonePage()
        }

        // on-click listener for button which will go back to the dev page (homepage)
        val imgvwHome = findViewById<ImageView>(R.id.imgvwHome)
        imgvwHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Setup event for Save & Exit button
        binding.ExitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setPatientInfo(patient: Patient) {
        binding.PatientName.text = patient.name
        binding.PatientDetails.text = String.format(patient.DOB + '\n' + patient.gender)
        val date = SimpleDateFormat("EEE, MMM d, yyyy").format(Date())
        val input = "Assessment Date: $date"
        val spannable = SpannableString(input)
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#6495ed")), 0, 17, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.AsasDt.text = spannable
    }

    private fun setPainResult(painResult: painData) {
        val paintxt1: String
        val paintxt2: String

        when (painResult.RPL) {
            -1 -> {
                paintxt1 = "No"
                paintxt2 = "No pain detected"
            }
            0 -> {
                paintxt1 = "No"
                paintxt2 = "No pain detected"
            }
            in 1..5 -> {
                paintxt1 = "Light"
                paintxt2 = "Pain level between 1 ~ 5 classified as light pain"
            }
            in 6..10 -> {
                paintxt1 = "Mild"
                paintxt2 = "Pain level between 6 ~ 10 classified as mild pain"
            }
            else -> {
                paintxt1 = "Strong"
                paintxt2 = "Pain level between 11 ~ 16 classified as Strong pain"
            }
        }

        // Convert to string and assign different color to them
        val lvl = painResult.RPL.toString()
        val str = "Pain level: ${lvl}, $paintxt1"
        fillintxt(str, paintxt1, paintxt2, lvl)
    }

    private fun fillintxt(str: String, t1: String, t2: String, lvl: String) {
        val spannable = SpannableString(str)

        // Color set
        val gr = ForegroundColorSpan(Color.parseColor("#24d605"))
        val re = ForegroundColorSpan(Color.RED)
        val yl = ForegroundColorSpan(Color.parseColor("#f57f17"))
        val bl = ForegroundColorSpan(Color.BLACK)

        // Set Color to text
        when (t1) {
            "Light" -> {
                spannable.setSpan(gr, 12, str.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                spannable.setSpan(
                    bl,
                    12 + lvl.length,
                    14 + lvl.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
            "Mild" -> {
                spannable.setSpan(yl, 12, str.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                spannable.setSpan(
                    bl,
                    12 + lvl.length,
                    14 + lvl.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
            "Strong" -> {
                spannable.setSpan(re, 12, str.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                spannable.setSpan(
                    bl,
                    12 + lvl.length,
                    14 + lvl.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
            else -> {
                spannable.setSpan(bl, 0, str.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
        }

        // Setting details of pain solution
        if (t1 == "No") {
            val str1 = "Pain detection: None"
            val spanstr = SpannableString(str1)
            spanstr.setSpan(bl, 0, str1.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            binding.PainTxtN.text = spanstr
            binding.PainDescripN.text = t2
        }
        else {
            binding.PainTxt.text = spannable
            binding.PainDescrip.text = t2
        }
    }

    private fun setArrowPlace(lvl: String) {
        // Reset page to status one
        reset.applyTo(binding.root)
        backStateOne()

        // Insert correct information
        val place: Float = lvl.toFloat() / 16.0f
        set.setHorizontalBias(binding.Arrow.id, place)
        set.applyTo(binding.root)
    }

    private fun setNonePage() {
        // Remove color bar, label and arrow
        set.setVisibility(binding.LegendBar.id, 4)
        set.setVisibility(binding.mini.id, 4)
        set.setVisibility(binding.max.id, 4)
        set.setVisibility(binding.Arrow.id, 4)
        set.setVisibility(binding.PainTxt.id, 4)
        set.setVisibility(binding.PainDescrip.id, 4)

        // Set textview for none pain visible
        set.setVisibility(binding.PainTxtN.id, 0)
        set.setVisibility(binding.PainDescripN.id, 0)

        // Apply to layout
        set.applyTo(binding.root)
    }

    private fun backStateOne() {
        set.clone(reset)
    }
}