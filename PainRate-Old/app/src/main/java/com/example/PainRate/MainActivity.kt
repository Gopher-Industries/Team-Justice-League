package com.example.PainRate

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.PainRate.accessingnet.PostClass
import com.example.PainRate.model.AnalysisResult
import okhttp3.OkHttp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("NumberGenerated", "Function has generated zero.");

        val btnScanning = findViewById<Button>(R.id.btnToScanningPage)
        btnScanning.setOnClickListener {
            val intent = Intent(this, Scanning::class.java)
            startActivity(intent)
        }

        val btnResults = findViewById<Button>(R.id.btnToResultsPage)
        btnResults.setOnClickListener {
//            val painResult = AnalysisResult(true, 4.9, "PainTest")
            val painResult = AnalysisResult(true, 6.4, "PainTest")
            val intent = Intent(this, Results::class.java)
            intent.putExtra(Results.RE, painResult)
            startActivity(intent)
        }
    }
}