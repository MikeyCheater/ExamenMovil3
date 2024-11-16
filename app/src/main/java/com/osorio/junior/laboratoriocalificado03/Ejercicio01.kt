package com.osorio.junior.laboratoriocalificado03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.osorio.junior.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchTeachers()
    }

    private fun fetchTeachers() {
        val api = RetrofitClient.instance.create(TeacherApi::class.java)
        api.getTeachers().enqueue(object : Callback<TeacherResponse> {
            override fun onResponse(call: Call<TeacherResponse>, response: Response<TeacherResponse>) {
                if (response.isSuccessful) {
                    val teachers = response.body()?.teachers ?: emptyList()
                    setupRecyclerView(teachers)
                }
            }

            override fun onFailure(call: Call<TeacherResponse>, t: Throwable) {
                // Manejo de errores
            }
        })
    }

    private fun setupRecyclerView(teachers: List<Teacher>) {
        binding.recyclerViewTeachers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTeachers.adapter = TeacherAdapter(this, teachers)
    }
}
