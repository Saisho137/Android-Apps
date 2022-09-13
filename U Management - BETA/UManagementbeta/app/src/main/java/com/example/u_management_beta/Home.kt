package com.example.u_management_beta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProm = findViewById<ImageButton>(R.id.btnProm)
        val btnFinanzas = findViewById<ImageButton>(R.id.btnFinanzas)
        val btnCalendario = findViewById<ImageButton>(R.id.btnCalendar)

        btnProm.setOnClickListener{
            startActivity(Intent(this,Promedios::class.java))
        }
        btnFinanzas.setOnClickListener{
            startActivity(Intent(this,Finanzas::class.java))
        }
        btnCalendario.setOnClickListener{
            startActivity(Intent(this,Calendario::class.java))
        }

    }

}