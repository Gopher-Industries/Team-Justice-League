package com.example.PainRate.painlevel

class painLevel {
    fun loadPainLevel(): painData{
        val reqId:Int = (0..100000).random()
        val binaryS:Int = (0..1).random()
        var painlvl:Int = (-1..16).random()

        while (painlvl == 0) {
            painlvl = (-1..16).random()
        }

        return if (binaryS == 0) {
            painData(reqId, binaryS, 0)

        } else {
            painData(reqId, binaryS, painlvl)
        }
    }
}