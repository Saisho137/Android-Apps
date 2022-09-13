package com.example.u_management_beta

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import java.util.*

class Calendario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)
        //Buttons
        val btnFechaI = findViewById<Button>(R.id.fechaI)
        val btnHoraI = findViewById<Button>(R.id.horaI)
        val btnFechaF = findViewById<Button>(R.id.fechaF)
        val btnHoraF  = findViewById<Button>(R.id.horaF)
        val btnAggCalendar = findViewById<Button>(R.id.aggCalendar)
        //EdiText
        val titulo = findViewById<EditText>(R.id.tEvento)
        val descripcion = findViewById<EditText>(R.id.dEvento)
        val lugar = findViewById<EditText>(R.id.lEvento)
        //ViewText
        val formatf = findViewById<TextView>(R.id.f1)
        val formatf2 = findViewById<TextView>(R.id.f3)
        val formath = findViewById<TextView>(R.id.f2)
        val formath2 = findViewById<TextView>(R.id.f4)
        //Variables
        var dia=0;var mes=0;var anno=0;var hora=0;var minuto=0
        var dia2=0;var mes2=0;var anno2=0;var hora2=0;var minuto2=0
        //Fnctns Buttons
        btnFechaI.setOnClickListener {
            val myCalendar = Calendar.getInstance()
            //
            anno = myCalendar.get(Calendar.YEAR)
            mes = myCalendar.get(Calendar.MONTH)
            dia = myCalendar.get(Calendar.DAY_OF_MONTH)
            //
            val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                anno = year
                mes = month + 1
                dia = dayOfMonth
                formatf.text="$dia/$mes/$anno"
            }
            DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        btnFechaF.setOnClickListener {
            val myCalendart = Calendar.getInstance()
            //
            anno2 = myCalendart.get(Calendar.YEAR)
            mes2 = myCalendart.get(Calendar.MONTH)
            dia2 = myCalendart.get(Calendar.DAY_OF_MONTH)
            //
            val datePickert = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                anno2 = year
                mes2 = month + 1
                dia2 = dayOfMonth
                formatf2.text="$dia2/$mes2/$anno2"
            }
            DatePickerDialog(this,datePickert,myCalendart.get(Calendar.YEAR),myCalendart.get(Calendar.MONTH),
                myCalendart.get(Calendar.DAY_OF_MONTH)).show()
        }
        btnHoraI.setOnClickListener {
            val myCalendar = Calendar.getInstance()
            //
            hora = myCalendar.get(Calendar.HOUR_OF_DAY)
            minuto = myCalendar.get(Calendar.MINUTE)
            //
            val timePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                hora = hourOfDay
                minuto = minute
                formath.text="$hora:$minuto"
            }
            TimePickerDialog(this,timePicker,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE),
                false).show()
        }
        btnHoraF.setOnClickListener {
            val myCalendart = Calendar.getInstance()
            //
            hora2 = myCalendart.get(Calendar.HOUR_OF_DAY)
            minuto2 = myCalendart.get(Calendar.MINUTE)
            //
            val timePickert = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                hora2 = hourOfDay
                minuto2 = minute
                formath2.text="$hora2:$minuto2"
            }
            TimePickerDialog(this,timePickert,myCalendart.get(Calendar.HOUR_OF_DAY),myCalendart.get(Calendar.MINUTE),
                false).show()
        }
        btnAggCalendar.setOnClickListener {
            val myCalendar1 = Calendar.getInstance()
            val myCalendar2 = Calendar.getInstance()
            var startIntent: Intent
            var estado = false
            while (!estado){
                try {
                    //Inicio Evento
                    myCalendar1.set(Calendar.YEAR,anno)
                    myCalendar1.set(Calendar.MONTH,mes)
                    myCalendar1.set(Calendar.DAY_OF_MONTH,dia)
                    myCalendar1.set(Calendar.HOUR_OF_DAY,hora)
                    myCalendar1.set(Calendar.MINUTE,minuto)
                    //Final Evento
                    myCalendar2.set(Calendar.YEAR,anno2)
                    myCalendar2.set(Calendar.MONTH,mes2)
                    myCalendar2.set(Calendar.DAY_OF_MONTH,dia2)
                    myCalendar2.set(Calendar.HOUR_OF_DAY,hora2)
                    myCalendar2.set(Calendar.MINUTE,minuto2)
                    //Conexion con Calendario de Google
                    startIntent = Intent(Intent.ACTION_EDIT)
                    startIntent.type = "vnd.android.cursor.item/event"
                    //Envio de fechas a Google Calendar
                    startIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,myCalendar1.timeInMillis)
                    startIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,myCalendar2.timeInMillis)
                    //Envio de info a Google Calendar
                    startIntent.putExtra(CalendarContract.Events.TITLE,titulo.text.toString())
                    startIntent.putExtra(CalendarContract.Events.DESCRIPTION,descripcion.text.toString())
                    startIntent.putExtra(CalendarContract.Events.EVENT_LOCATION,lugar.text.toString())
                    //
                    startActivity(startIntent)
                    //Fin del While
                    estado = true
                }catch (e: Exception){
                    formatf.text = ""
                    formatf2.text = ""
                    formath.text = ""
                    formath2.text = ""
                    val alerta = AlertDialog.Builder(this)
                        .setTitle("Error!").setMessage("Fecha invalida!")
                    alerta.show()
                }
            }
        }
    }
}