package com.example.PainRate

import com.example.PainRate.utils.JsonMapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class TEsting {
    private val setIP = "35.238.64.169"
    private lateinit var conn: HttpURLConnection
    private lateinit var buffer: StringBuffer

    fun conTest() {
        val url = URL("http://${this.setIP}:5000/test")

        conn = url.openConnection() as HttpURLConnection
        conn.useCaches = false
        conn.doOutput = true
        conn.requestMethod = "POST"

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

//        val solut = buffer.toString()
//        println(solut)
        val analysisResult = JsonMapper.mapToAnalysisResult(buffer.toString())
        println("analysisResult:id=${analysisResult.id},Pain=${analysisResult.pain}, PainRate=${analysisResult.painRate}")
    }

}