package com.example.PainRate.utils


import kotlinx.serialization.*
import com.example.PainRate.model.AnalysisResult
import kotlinx.serialization.json.Json

class JsonMapper {
    companion object {
        fun mapToAnalysisResult(jsonStr: String): AnalysisResult {
            val obj =  Json.decodeFromString<AnalysisResult>(jsonStr)
            return obj
        }
    }
}