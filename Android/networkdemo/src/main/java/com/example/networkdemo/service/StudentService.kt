package com.example.networkdemo.service

import com.example.networkdemo.model.Student
import retrofit2.Call
import retrofit2.http.POST

interface StudentService {
    @POST("listStudents")
    fun getStudentData(): Call<List<Student>>
}