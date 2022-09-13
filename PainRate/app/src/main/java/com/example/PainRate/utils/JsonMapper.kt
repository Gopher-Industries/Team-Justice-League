package com.example.PainRate.utils
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import com.example.PainRate.model.AnalysisResult

class JsonMapper {
    companion object {
        fun mapToAnalysisResult(jsonStr: String): AnalysisResult {
            val obj = Json.decodeFromString<AnalysisResult>(jsonStr)
            return obj;
        }
    }
}