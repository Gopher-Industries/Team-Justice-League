package com.example.PainRate.accessingnet

import android.graphics.Bitmap
import android.icu.util.Output
import android.util.Log
import com.example.PainRate.model.AnalysisResult
import com.example.PainRate.utils.JsonMapper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class PostClass() {
    // TODO: Everytime restart server, please modify the URL link
    private val setIP = "34.122.238.212"

    // Send target image to assessment module
    fun clientOkHttp(FILE_IMAGE: File): String {
        val url = URL("http://${this.setIP}:5000/getPainRate")
        val client = OkHttpClient()

        // Set data to sent through connection
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("identity","20220927001")
            .addFormDataPart("image", "photo.jpg", ProgressRequestBody("image".toMediaTypeOrNull(), FILE_IMAGE))
            .addFormDataPart("model_type","model3")
            .build()

        // Set up connection
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        val call = client.newCall(request)

        // variable to receive result
        var result = ""

        // Start connection
        try{
            val response = call.execute()
            if (response.isSuccessful){
                Log.i("PAIN RESULTS", "Response Success")
                val recbody = response.body
                result = recbody!!.string()
            }
            response.close()
        } catch (e:Exception){
            println(e)
            Log.i("PAIN RESULTS", e.toString())
        }
        return result
    }

    // Send base image to base module
    fun BaseOkHttp(FILE_IMAGE: File){
        val url = URL("http://${this.setIP}:5000/sendBaseImage")
        val client = OkHttpClient()

        // Set data to sent through connection
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("identity","Patient")
            .addFormDataPart("image", "photo.jpg", ProgressRequestBody("image/*".toMediaTypeOrNull(), FILE_IMAGE))
            .build()

        // Set up connection
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        val call = client.newCall(request)

        // Start connection
        try{
            val response = call.execute()
            if (response.isSuccessful){
                val recbody = response.body
                println(recbody!!.string())
                Log.i("PAIN RESULTS", "RESPONSE SUCCESS")
            }
            response.close()
        } catch (e:Exception){
            println(e)
            Log.i("PAIN RESULTS", e.toString())
        }
    }

    // Alternative function to convert bitmap into byte array
    fun btimaptoBytes(bitmap: Bitmap):ByteArray{
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }
}