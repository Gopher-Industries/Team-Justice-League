package com.example.PainRate.accessingnet

import android.graphics.Bitmap
import android.icu.util.Output
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
    // Constant values
    private val attachmentName = "image"
    private val boundary = "*****"
    private val preFix = "\r\n--"+ this.boundary+"--\r\n"

    // TODO: Everytime restart server, please modify the URL link
    private val setIP = "34.67.21.9"

    // Initialize variable
    private lateinit var conn: HttpURLConnection
    private lateinit var os: DataOutputStream

    fun clientOkHttp(FILE_IMAGE: File){
        val url = URL("http://${this.setIP}:5000/getPainRate")
        println(FILE_IMAGE.toString())
        //val url ="http://${this.setIP}:5000/"
        println(url)
        println("Setting body")
        val client = OkHttpClient()
        //val body = ProgressRequestBody("text/x-markdown; charset=utf-8".toMediaTypeOrNull(), "Hello")
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("identity","testing_v1")
            .addFormDataPart("image", "photo.jpg", ProgressRequestBody("image/*".toMediaTypeOrNull(), FILE_IMAGE))
            .build()

        println("body successful")
        println("Setting up request")
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        println("Set call")
        val call = client.newCall(request)

        println("call started")
        try{
            val response = call.execute()
            println("Trying to get response")
            println(response.isSuccessful.toString())
            if (response.isSuccessful){
                val recbody = response.body
                println(recbody!!.string())
            }
            response.close()
        } catch (e:Exception){
            println(e)
        }
    }

    fun btimaptoBytes(bitmap: Bitmap):ByteArray{
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }
}