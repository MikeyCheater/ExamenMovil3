package com.osorio.junior.laboratoriocalificado03

import retrofit2.Call
import retrofit2.http.GET

interface TeacherApi {
    @GET("list/teacher")
    fun getTeachers(): Call<TeacherResponse> // Cambiado para adaptarse a la estructura del JSON
}

data class TeacherResponse(
    val teachers: List<Teacher>
)
