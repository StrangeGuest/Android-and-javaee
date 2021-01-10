package com.example.networkdemo.util

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

object FileUtil {
    public fun getStringFromInputStream(input:InputStream):String{
        val resultStr:StringBuilder=java.lang.StringBuilder()
        val inputStreamReader=InputStreamReader(input)
        val breader=BufferedReader(inputStreamReader)
        breader.use {
            breader.forEachLine {
                resultStr.append(it)
            }
        }
        return resultStr.toString()
    }
}