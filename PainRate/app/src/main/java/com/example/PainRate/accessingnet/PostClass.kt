package com.example.PainRate.accessingnet

import android.graphics.Bitmap
import com.example.PainRate.model.AnalysisResult
import com.example.PainRate.utils.JsonMapper
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class PostClass(val img: Bitmap) {
    // Constant values
    private val attachmentName = "photo"
    private val attachmentFileName = "photo.bmp"
    private val boundary = "*****"
    private val crlf = "\r\n"
    private val twoHyphens = "--"

    // TODO: Everytime restart server, please modify the URL link
    private val serIP = "0.0.0.0"

    // Initialize variable
    private lateinit var conn: HttpURLConnection
    private lateinit var os: DataOutputStream
    private lateinit var buffer: StringBuffer

    fun setConnection(): AnalysisResult {
        // Set server URL
        val url = URL("https://${this.serIP}")

        // Set bitmap image to byte array
        val stream = ByteArrayOutputStream()
        this.img.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        val image = stream.toByteArray()

        // Set connection part
        try {
            // Set connection attribute
            conn = url.openConnection() as HttpURLConnection
            conn.useCaches = false
            conn.doOutput = true
            conn.requestMethod = "POST"
            conn.setRequestProperty("Connection", "Keep-Alive")
            conn.setRequestProperty("Cache-Control", "no-cache")
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + this.boundary)

            // Set content in side buffer
            os = DataOutputStream(conn.outputStream)
            os.writeBytes(this.twoHyphens + this.boundary + this.crlf)
            os.writeBytes(
                "Content-Disposition: form-data; name=\"" +
                        this.attachmentName + "\";filename=\"" +
                        this.attachmentFileName + "\"" + this.crlf
            )
            os.writeBytes(this.crlf)
            os.write(image)
            os.writeBytes(this.crlf)
            os.writeBytes(this.twoHyphens + this.boundary + this.twoHyphens + this.crlf)

            // Flush output buffer
            os.flush()
            os.close()

            // Receiving part
            val charset = "utf-8"
            val inStream = BufferedReader(InputStreamReader(conn.inputStream, charset))
            buffer = StringBuffer()
            inStream.use { br ->
                val temp = br.readLine()
                if (temp != null) {
                    buffer.append(temp)
                }
            }
            inStream.close()

            // Decode json
            // Testing for print result read from json file
            // val analysisResult = JsonMapper.mapToAnalysisResult(buffer.toString())
            // println("analysisResult:id=${analysisResult.id},Pain=${analysisResult.pain}, PainRate=${analysisResult.painRate}")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (conn != null) {
                conn.disconnect()
            }
        }
        // Return Json file as dataset for static page
        return JsonMapper.mapToAnalysisResult(buffer.toString())
    }
}