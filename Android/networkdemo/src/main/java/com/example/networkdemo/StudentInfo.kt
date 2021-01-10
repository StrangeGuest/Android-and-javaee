package com.example.networkdemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_picture_test_activiy.*
import kotlinx.android.synthetic.main.activity_student_info.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class StudentInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)

        val bundle = intent.getBundleExtra("Student")
        val no = bundle?.getString("stuNo")
        val name = bundle?.getString("stuName")
        val classes = bundle?.getString("classes")
        val gender = bundle?.getString("gender")
        val department = bundle?.getString("department")
        val tel = bundle?.getString("tel")
        val dormno = bundle?.getString("dormNo")
        val photopath=bundle?.getString("photoPath")
        val textView=findViewById<View>(R.id.stuno) as TextView
        val textView2=findViewById<View>(R.id.stuname) as TextView
        val textView3=findViewById<View>(R.id.classes) as TextView
        val textView4=findViewById<View>(R.id.gender) as TextView
        val textView5=findViewById<View>(R.id.department) as TextView
        val textView6=findViewById<View>(R.id.tel) as TextView
        val textView7=findViewById<View>(R.id.dromno) as TextView

        val url="http://121.4.12.60:8080/"+photopath
        val downloadImage=1
        val handler=object : Handler(){
            override fun handleMessage(msg: Message) {
                //更新imageView
                when(msg.what){
                    downloadImage->{
                        val image=msg.obj as Bitmap
                        imageView4.setImageBitmap(image)
                    }
                }
            }
        }

        thread {
            val urlConnection= URL(url)
            var connection = urlConnection.openConnection() as HttpURLConnection
            connection.connectTimeout=5000

            //响应
            if(connection.responseCode== HttpURLConnection.HTTP_OK){
                //获取输入流，就是读取图片
                var inputStream = connection.inputStream
                //把流转换为图片文件
                val image= BitmapFactory.decodeStream(inputStream)
                //创建消息
                val msg=Message()
                //设置参数
                msg.what=downloadImage
                msg.obj=image
                handler.sendMessage(msg)
            }
        }

        textView.text=no
        textView2.text=name
        textView3.text=classes
        textView4.text=gender
        textView5.text=department
        textView6.text=tel
        textView7.text=dormno
    }
}