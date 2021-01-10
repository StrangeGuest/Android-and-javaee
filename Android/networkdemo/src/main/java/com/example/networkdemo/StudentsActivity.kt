package com.example.networkdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkdemo.adapter.StudentAdapter
import com.example.networkdemo.model.Student
import com.example.networkdemo.model.Student2
import com.example.networkdemo.util.HttpUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_students.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class StudentsActivity : AppCompatActivity() {

    private val studentsList=ArrayList<Student2>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)
        var students:List<Student>?=null
        HttpUtil.sendOkHttpRequest("http://121.4.12.60:8080/ListStudentServlet2",object:
            Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity","发生错误")
            }

            override fun onResponse(call: Call, response: Response) {
                val resultData=response.body?.string()
                val type=object: TypeToken<List<Student>>(){}.type

                students=Gson().fromJson<List<Student>>(resultData,type)
                //studentsList.add(Student2(students?.get(0).stuNo,students?.get(0).stuName,R.drawable.apple_pic))

            }

        })


        button7.setOnClickListener {
            if(students!=null){
                //Toast.makeText(this,students?.get(2)?.stuName, Toast.LENGTH_LONG).show()
                for(i in 0..30){
                    if(students?.get(i)!!.gender=="男"){

                studentsList.add(Student2(students?.get(i)!!.stuNo,students?.get(i)!!.stuName, R.drawable.boy))
                    }else{
                        studentsList.add(Student2(students?.get(i)!!.stuNo,students?.get(i)!!.stuName, R.drawable.girls))

                    }

                }

                var linearLayoutManager = LinearLayoutManager(this)
                recyclerview.layoutManager=linearLayoutManager
                var adapter = StudentAdapter(studentsList)
                recyclerview.adapter=adapter


            }
        }
        button3.setOnClickListener {
            val intent=Intent(this,SearchStudent::class.java)
            startActivity(intent)
        }

    }
}