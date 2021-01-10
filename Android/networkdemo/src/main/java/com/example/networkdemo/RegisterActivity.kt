package com.example.networkdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.networkdemo.model.ResultOV
import com.example.networkdemo.util.HttpUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        submitRegister.setOnClickListener {
//            val intent= Intent(this,MainActivity::class.java)
//            startActivity(intent)
            val requestData=HashMap<String,String>()
            requestData.put("username",name1.text.toString())
            requestData.put("password",password.text.toString())
            sendRequestWithOkHttp("http://121.4.12.60:8080/userRegister","post",requestData)
            HttpUtil.sendOkHttpRequest("http://121.4.12.60:8080/userRegister",requestData,object:
                Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("MainActivity","发生错误")
                }

                override fun onResponse(call: Call, response: Response) {
                    val resultData=response.body?.string()
                    var fromJson = Gson().fromJson<ResultOV>(resultData, ResultOV::class.java)
                    if(fromJson.result=="ok"){
                        Log.e("Main","success")
                        var intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Log.e("Main","error")

                    }
                }

            })
        }
    }

    private fun sendRequestWithOkHttp(url:String,method:String,data:Map<String,String>){
        thread {
            try {
                val client= OkHttpClient()
                val username=data.get("username").toString()
                val password=data.get("password").toString()
                val requestBody= FormBody.Builder()
                    .add("username",username)
                    .add("password",password)
                    .build()
                val request= Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if(responseData!=null){
                    dealResponse(responseData)
                }


            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun dealResponse(responseData:String){
        Log.d("MainActivity",responseData)
        Toast.makeText(this,responseData, Toast.LENGTH_LONG).show()
        var result = Gson().fromJson(responseData, ResultOV::class.java)
        if(result.result=="ok"){
            val intentOK: Intent=Intent(this,MainActivity::class.java)

            startActivity(intentOK)
        }else if(result.result=="false"){
            //跳转到main2
            val intentError: Intent=Intent(this,MainActivity2::class.java)

            startActivity(intentError)
        }
    }


}