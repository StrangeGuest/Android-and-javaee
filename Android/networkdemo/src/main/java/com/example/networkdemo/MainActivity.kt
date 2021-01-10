package com.example.networkdemo

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.networkdemo.model.ResultOV
import com.example.networkdemo.util.FileUtil
import com.example.networkdemo.util.HttpUtil
import com.example.networkdemo.util.MyDatabaseHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       // val dbHelper=MyDatabaseHelper(this,"user.db",2)

        bt_submit.setOnClickListener {
            //startActivity(Intent(this,FifthActivity::class.java))
            //1，获取用户名密码
            //val data="username=${ev_username.text.toString()}&password=${ev_password.text.toString()}"
            //Toast.makeText(this,data,Toast.LENGTH_LONG).show()
            // sentRequestWithHttpURLConnection(data)
            val requestData=HashMap<String,String>()
            requestData.put("username",ev_username.text.toString())
            requestData.put("password",ev_password.text.toString())
            sendRequestWithOkHttp("http://121.4.12.60:8080/userLogin","post",requestData)
            HttpUtil.sendOkHttpRequest("http://121.4.12.60:8080/userLogin",requestData,object:Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("MainActivity","发生错误")
                }

                override fun onResponse(call: Call, response: Response) {
                    val resultData=response.body?.string()
                    var fromJson = Gson().fromJson<ResultOV>(resultData, ResultOV::class.java)
                    if(fromJson.result=="ok"){
                        Log.e("Main","success")
                        var intent = Intent(this@MainActivity, StudentsActivity::class.java)
                        startActivity(intent)
                    }else{
                        Log.e("Main","error")

                    }
                }

            })
        }

        register.setOnClickListener {
            var intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

//        button6.setOnClickListener {
//            var intent=Intent(this,PictureTestActiviy::class.java)
//            startActivity(intent)
//        }
//        button4.setOnClickListener {
////            var dbHelper=MyDatabaseHelper(this,"user.db",2)
////            dbHelper.writableDatabase
////            Toast.makeText(this,"ok",Toast.LENGTH_LONG).show()
//            val db=dbHelper.writableDatabase
//            val v1=ContentValues().apply {
//            put("username","zhangsan")
//            put("password","123456")
//        }
//            db.insert("tbl_user",null,v1)
//
//            val v2=ContentValues().apply {
//                put("username","lisi")
//                put("password","123")
//            }
//            db.insert("tbl_user",null,v2)
//        }
//
//        button3.setOnClickListener {
//            //更新数据
//            var db=dbHelper.writableDatabase
//            val v2=ContentValues().apply {
//                put("password","108630")
//
//            }
//            //把lisi的密码改为108630
//            db.update("tbl_user",v2,"username=?", arrayOf("lisi"))
//
//        }
    }

    //安卓的网络编程
    /*
    * HttpURLConnection
    *
    * val url=URL("http://106.15.192.167:8080/userLogin")
    * val conn=url.openConnection() as HttpURLConnection
    * conn.requestMethod="POST"
    * conn.connectionTimeout=8000
    * conn.readTimeout=8000
    * */
    private fun sentRequestWithHttpURLConnection(data:String):Unit{
        //开启一个新的线程开启网络请求
        thread {
            var conn:HttpURLConnection?=null
            try {
                //1,获取连接
                val url = URL("http://106.15.192.167:8080/userLogin")
                conn = url.openConnection() as HttpURLConnection
                //2，设置连接参数
                conn.requestMethod="POST"
                conn.connectTimeout=8000
                conn.readTimeout=8000
                //3,发送数据，获取输出流
                var output = conn.outputStream
                //4,以字节数组方式发送数据
                output.write(data.toByteArray())
                //5，立即发送
                output.flush()
                //6，获取发送状态码
                var responseCode = conn.responseCode
                //7,对状态码进行判断
                if(responseCode==200){
                    //8,接收数据
                    var input = conn.inputStream
                    var result=FileUtil.getStringFromInputStream(input)
                    Log.e("MainActivity",result)
                    var resultOV = Gson().fromJson<ResultOV>(result, ResultOV::class.java)
                    if(resultOV.result=="ok"){
                        //成功
                        //跳转到Second页面
                        val intentOK: Intent=Intent(this,SecondActivity::class.java)

                        startActivity(intentOK)
                    }else{
                        //失败，跳转到main2
                        val intentError: Intent=Intent(this,MainActivity2::class.java)

                        startActivity(intentError)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }finally {

            }

        }
    }

    private fun sendRequestWithOkHttp(url:String,method:String,data:Map<String,String>){
        thread {
            try {
                val client=OkHttpClient()
                val username=data.get("username").toString()
                val password=data.get("password").toString()
                val requestBody=FormBody.Builder()
                    .add("username",username)
                    .add("password",password)
                    .build()
                val request=Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if(responseData!=null){
                    dealResponse(responseData)
                }


            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun dealResponse(responseData:String){
        Log.d("MainActivity",responseData)
        Toast.makeText(this,responseData,Toast.LENGTH_LONG).show()
        var result = Gson().fromJson(responseData, ResultOV::class.java)
        if(result.result=="ok"){
            val intentOK: Intent=Intent(this,SecondActivity::class.java)

            startActivity(intentOK)
        }else if(result.result=="false"){
            //跳转到main2
            val intentError: Intent=Intent(this,MainActivity2::class.java)

            startActivity(intentError)
        }
    }
}
