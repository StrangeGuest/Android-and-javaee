package com.example.networkdemo.model

class Student2 (
    val stuNo:String,
    val stuName:String,
    val photoPath:Int){

    override fun toString(): String {
        return "学号: "+stuNo+"\n姓名:  "+stuName
    }
}


