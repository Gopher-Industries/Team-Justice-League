package com.example.PainRate.accessingnet

import android.icu.util.Output
import com.example.PainRate.model.AnalysisResult
import com.example.PainRate.utils.JsonMapper
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class PostClass(val img: File) {
    // Constant values
    private val attachmentName = "image"
    private val boundary = "*****"
    private val preFix = "\r\n--"+ this.boundary+"--\r\n"

    // TODO: Everytime restart server, please modify the URL link
    private val setIP = "35.238.64.169"

    // Initialize variable
    private lateinit var conn: HttpURLConnection
    private lateinit var os: DataOutputStream

    // private var buffer = StringBuffer()


//    fun ConnTest() {
//        // Set server URL
//        val url = URL("http://${this.setIP}:5000/test")
//
//        conn = url.openConnection() as HttpURLConnection
//        conn.useCaches = false
//        conn.doOutput = true
//        conn.requestMethod = "POST"
//
//        val responseCode = conn.responseCode
//        println(responseCode)
//    }

    fun setConnection(): AnalysisResult {
        // Set server URL
        val url = URL("http://${this.setIP}:5000/getPainRate")
//        val url = URL("http://${this.setIP}:5000/sendBaseImage")
//        val url = URL("http://${this.setIP}:5000/test")
        val buffer = StringBuffer()
        val painResult = AnalysisResult(true, 16.0, "PainTest")
        // Set connection part
        try {
            // Set connection attribute
            conn = url.openConnection() as HttpURLConnection
            conn.doOutput = true
            conn.doInput = true
            conn.useCaches = false
            conn.connectTimeout = 5000
            conn.readTimeout = 30000

            // Request type
            conn.requestMethod = "POST"

            // Request Header
            // conn.setRequestProperty("Accept", "*/*")
            conn.setRequestProperty("Connection", "Keep-Alive")
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + this.boundary)

            // Set content in side payload
            os = DataOutputStream(conn.outputStream)

            // Use stringbuffer to store data to be send
            val strbuf = StringBuffer()
            strbuf.append(preFix)
            strbuf.append("Content-Disposition: from-data; name=\"image\"; model_type: model1") // filename=\"" + this.attachmentName +"\"\r\n")
            strbuf.append("Content-type: image/jpeg"+"\r\n\r\n")
            os.write(strbuf.toString().toByteArray())


            // Preparing uploading image
            val fileInputStream = FileInputStream(img)
            val imgStream = DataInputStream(fileInputStream)
            val fileSize: Int = fileInputStream.available()
            val bufferOut = ByteArray(fileSize)
            imgStream.readFully(bufferOut)
            os.write(bufferOut, 0, fileSize)

            // Close image stream
            imgStream.close()
            fileInputStream.close()

            os.write(preFix.toByteArray())

            // Flush output buffer
            os.flush()

            // Print what inside outputstream
            println("Solution from conn.outputstream")
            val outStream = BufferedReader(InputStreamReader(os, charset) )
            println(conn.outputStream.toString())
            println("Solution from os")
            println(os.toString())

            os.close()

            // Receiving part
            val charset = "utf-8"
            val inStream = BufferedReader(InputStreamReader(conn.inputStream, charset))
            // println(conn.inputStream.toString())
            inStream.use { br ->
                val temp = br.readLine()
                if (temp != null) {
                    buffer.append(temp)
                }
            }
            inStream.close()

            println("Test Result: ")
            println(buffer.toString())

            // Decode json
            // Testing for print result read from json file
            // val analysisResult = JsonMapper.mapToAnalysisResult(buffer.toString())
            // println("analysisResult:id=${analysisResult.id},Pain=${analysisResult.pain}, PainRate=${analysisResult.painRate}")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            conn.disconnect()
        }
        // Return Json file as dataset for static page

        // return JsonMapper.mapToAnalysisResult(buffer.toString())
        return painResult
    }
}