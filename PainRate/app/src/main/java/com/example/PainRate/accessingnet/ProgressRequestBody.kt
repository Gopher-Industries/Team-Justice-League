package com.example.PainRate.accessingnet

import android.icu.util.UniversalTimeScale.toLong
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.lang.Exception

class ProgressRequestBody(
    private val contentType: MediaType?,
    //private val str:String
//    private val file: ByteArray
    private val file : File
    // private val lisenter: FileUtils.ProgressListener
): RequestBody() {
    override fun contentType(): MediaType? {
        return contentType
    }

    override fun contentLength(): Long{
        //return str.length.toLong()
        return file.length().toLong()
    }

    override fun writeTo(sink: BufferedSink){
        try {
            val max = contentLength()
            val current = 0L
//            lisenter.onStart()
            // sink.write(str.toByteArray())
            sink.write(file.readBytes())
//            lisenter.onComplete()
        } catch (e:Exception){
            println("Error $e")
        }
    }
}