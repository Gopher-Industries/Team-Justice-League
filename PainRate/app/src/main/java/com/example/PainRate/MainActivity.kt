package com.example.PainRate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.PainRate.model.AnalysisResult

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScanning = findViewById<Button>(R.id.btnToScanningPage)
        btnScanning.setOnClickListener {
            val intent = Intent(this, Scanning::class.java)
            startActivity(intent)
        }

        val btnResults = findViewById<Button>(R.id.btnToResultsPage)
        btnResults.setOnClickListener {
            val painResult = AnalysisResult(true, 4.9, "PainTest")
            val intent = Intent(this, Results::class.java)
            intent.putExtra(Results.RE, painResult)
            startActivity(intent)
        }
    }
}