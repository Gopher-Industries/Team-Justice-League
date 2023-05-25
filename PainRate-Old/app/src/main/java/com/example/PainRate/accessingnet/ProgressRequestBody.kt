package com.example.PainRate.accessingnet

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.lang.Exception

class ProgressRequestBody(
    private val contentType: MediaType?,
    private val file : File
): RequestBody() {
    override fun contentType(): MediaType? {
        return contentType
    }

    override fun contentLength(): Long{
        return file.length()
    }

    override fun writeTo(sink: BufferedSink){
        try {
            sink.write(file.readBytes())
        } catch (e:Exception){
            println("Error $e")
        }
    }
}