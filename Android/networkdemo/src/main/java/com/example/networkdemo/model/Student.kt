package com.example.networkdemo.model

class Student(
    val stuNo:String,
    val stuName:String,
    val classes: String,
    val gender:String,
    val department:String,
    val tel:String,
    val dormNo:String,
    val photoPath:String){
    override fun toString(): String {
        //return stuName;
        return "学号: "+stuNo+"\n"+"姓名:  "+stuName+"\n"+"班级:  "+classes+"\n性别:  "+gender+"\n专业:  "+department+"\n电话:  "+tel+"\n宿舍号:  "+dormNo;
    }


}