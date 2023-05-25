package com.example.PainRate.model

import kotlinx.serialization.*

@Serializable
data class AnalysisResult(
    @SerialName("Pain")
    val pain: Boolean,
    @SerialName("PainRate")
    var painRate: Double,
    @SerialName("id")
    val id: String
) : java.io.Serializable