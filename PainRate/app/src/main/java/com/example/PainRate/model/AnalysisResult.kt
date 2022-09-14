package com.example.PainRate.model
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class AnalysisResult(
    val id: String,
    @SerialName("Pain")
    val pain: Boolean,
    @SerialName("PainRate")
    val painRate: Int
) : java.io.Serializable