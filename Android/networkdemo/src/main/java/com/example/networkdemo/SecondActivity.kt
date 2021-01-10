package com.example.networkdemo

import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.networkdemo.model.Student
import com.example.networkdemo.util.HttpUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class SecondActivity : AppCompatActivity() {

    //private var students=ArrayList<Student>();
//    private fun initStudents(){
//        var students2:List<Student>?=null
//        HttpUtil.sendOkHttpRequest("http://106.15.192.167:8080/listStudents",object:
//            Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("MainActivity","发生错误")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val resultData=response.body?.string()
//                val type=object:TypeToken<List<Student>>(){}.type
//
//                students2 = Gson().fromJson<List<Student>>(resultData, type)
//
//                if(students2!=null){
//                    repeat(1){
//
//                    }
//                }
//
//            }
//
//        })
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var students:List<Student>?=null
        HttpUtil.sendOkHttpRequest("http://121.4.12.60:8080/ListStudentServlet2",object:
            Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity","发生错误")
            }

            override fun onResponse(call: Call, response: Response) {
                val resultData=response.body?.string()
                val type=object:TypeToken<List<Student>>(){}.type

                students = Gson().fromJson<List<Student>>(resultData, type)
//                students = Gson().fromJson<List<Student>>(resultData, type)


            }

        })
        second_btn01.setOnClickListener {
            if(students!=null){
                Toast.makeText(this,students?.get(2)?.stuName,Toast.LENGTH_LONG).show()

        }
    }}
}