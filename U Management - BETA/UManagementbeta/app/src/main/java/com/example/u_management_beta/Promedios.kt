package com.example.u_management_beta

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Promedios : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedios)

        val btnCalculo = findViewById<Button>(R.id.Calcular)
        val btnAdd = findViewById<Button>(R.id.AddScore)
        val btnRemove = findViewById<Button>(R.id.RemoveScore)
        val irSemestre = findViewById<Button>(R.id.switchMateria)
        val irHome = findViewById<Button>(R.id.Home)

        val arrayNotas = arrayListOf<Double>()
        val arrayPorcentajes = arrayListOf<Double>()

        val preNota = findViewById<EditText>(R.id.nota)
        val prePorcentaje = findViewById<EditText>(R.id.porcentaje)

        val contador = findViewById<TextView>(R.id.cantNotas)
        val resultado = findViewById<TextView>(R.id.Score)

        var cont = 0

        btnAdd.setOnClickListener{
            try{

                val notas: Double = preNota.text.toString().toDouble()
                arrayNotas.add(notas)
                val porcentajes: Double = prePorcentaje.text.toString().toDouble()
                arrayPorcentajes.add(porcentajes)
                cont += 1
                contador.text = "Llevas \'$cont\' notas promediadas"
                val alerta = AlertDialog.Builder(this)
                    .setTitle("Listo!").setMessage("Se ha guardado la nota! $cont")
                alerta.show()

            } catch (e: NumberFormatException) {
                val alerta = AlertDialog.Builder(this)
                    .setTitle("Error!").setMessage("Dejaste al menos un campo vacío!")
                alerta.show()
            }
            preNota.setText("")
            prePorcentaje.setText("")
        }
        btnRemove.setOnClickListener {
            try{
                //falta una excepcion mejor
                arrayNotas.removeLast()
                arrayPorcentajes.removeLast()
                cont -= 1
                contador.text = "Llevas \'$cont\' notas promediadas"
                val alerta = AlertDialog.Builder(this)
                    .setTitle("Listo!").setMessage("La última nota se ha eliminado con éxito! $cont")
                alerta.show()
            } catch (e: IndexOutOfBoundsException){
                val alerta = AlertDialog.Builder(this)
                    .setTitle("Error!").setMessage("Aún no has guardado ninguna Nota!")
                alerta.show()
            }
            preNota.setText("")
            prePorcentaje.setText("")
        }
        btnCalculo.setOnClickListener{
            var acumuladorNota = 0.0
            var acumuladorTotal = 0.0
            try {
                for (i in arrayNotas.indices){
                    acumuladorTotal += acumuladorNota
                    acumuladorNota = arrayNotas[i] * arrayPorcentajes[i]
                }
                acumuladorTotal += acumuladorNota
                acumuladorTotal /= 100
                resultado.text = "$acumuladorTotal"
            } catch (e: IndexOutOfBoundsException){
                val alerta = AlertDialog.Builder(this)
                    .setTitle("Error!").setMessage("Aún no has guardado ninguna Nota!")
                alerta.show()
            }
            preNota.setText("")
            prePorcentaje.setText("")
        }
        irSemestre.setOnClickListener{
            startActivity(Intent(this,Semestre::class.java))
        }
        irHome.setOnClickListener{
            startActivity(Intent(this,Home::class.java))
        }
    }

}