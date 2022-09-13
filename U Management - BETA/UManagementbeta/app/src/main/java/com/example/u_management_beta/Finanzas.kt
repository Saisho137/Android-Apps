package com.example.u_management_beta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class Finanzas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finanzas)

        //Persistencia
        val prefs = Prefs(this)
        //Botones principales
        val btnInc = findViewById<Button>(R.id.btnIncome)
        val btnEgr = findViewById<Button>(R.id.btnEgress)
        val btnWipe = findViewById<Button>(R.id.btnWipe)
        //Categorias
        val btnBill = findViewById<Button>(R.id.btnBill)
        val btnHealth = findViewById<Button>(R.id.btnHealth)
        val btnTransport = findViewById<Button>(R.id.btnTransport)
        val btnShop = findViewById<Button>(R.id.btnShop)
        val btnOut = findViewById<Button>(R.id.btnOut)
        val btnOther = findViewById<Button>(R.id.btnOther)
        //
        setInvisible(btnBill,btnHealth,btnTransport,btnShop,btnOut,btnOther)
        //Textos
        val ingFin = findViewById<EditText>(R.id.ingFinanza)
        val income = findViewById<TextView>(R.id.income)
        val egress = findViewById<TextView>(R.id.egress)
        val balance = findViewById<TextView>(R.id.balance)
        val porcents = findViewById<TextView>(R.id.distribucion)
        //Actualizar texto
        income.text = prefs.getInc().toString()
        egress.text = prefs.getEgr().toString()
        prefs.saveBal(prefs.getInc() - prefs.getEgr())
        balance.text = prefs.getBal().toString()
        prefs.actualizar()
        porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
        //Botones
        btnInc.setOnClickListener {
            if (ingFin.text.isNotEmpty()){
                val temp = ingFin.text.toString().toInt()
                val cont = prefs.getInc() + temp
                prefs.saveInc(cont)
                prefs.saveBal(prefs.getInc() - prefs.getEgr())
                income.text = prefs.getInc().toString()
                balance.text = prefs.getBal().toString()
            }else{
                egress.text = prefs.getEgr().toString()
                balance.text = prefs.getBal().toString()
                val alerta = AlertDialog.Builder(this)
                    .setTitle("Error!").setMessage("Debes digitar un monto!")
                alerta.show()
            }
        }
        btnEgr.setOnClickListener {
            if (ingFin.text.isNotEmpty()){
                val temp = ingFin.text.toString().toInt()
                val cont = prefs.getEgr() + temp
                prefs.saveEgr(cont)
                prefs.saveBal(prefs.getInc() - prefs.getEgr())
                egress.text = prefs.getEgr().toString()
                balance.text = prefs.getBal().toString()
                //Category's
                btnBill.visibility = View.VISIBLE
                btnHealth.visibility = View.VISIBLE
                btnTransport.visibility = View.VISIBLE
                btnShop.visibility = View.VISIBLE
                btnOut.visibility = View.VISIBLE
                btnOther.visibility = View.VISIBLE
                //functionality
                btnBill.setOnClickListener {
                    val temp = ingFin.text.toString().toInt()
                    val cont = prefs.getBill() + temp
                    prefs.saveBill(cont)
                    prefs.saveBillP()
                    setInvisible(btnBill,btnHealth,btnTransport,btnShop,btnOut,btnOther)
                    prefs.actualizar()
                    porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
                }
                btnHealth.setOnClickListener {
                    val temp = ingFin.text.toString().toInt()
                    val cont = prefs.getHealth() + temp
                    prefs.saveHealth(cont)
                    setInvisible(btnBill,btnHealth,btnTransport,btnShop,btnOut,btnOther)
                    prefs.saveHealthP()
                    prefs.actualizar()
                    porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
                }
                btnTransport.setOnClickListener {
                    val temp = ingFin.text.toString().toInt()
                    val cont = prefs.getTransport() + temp
                    prefs.saveTransport(cont)
                    setInvisible(btnBill,btnHealth,btnTransport,btnShop,btnOut,btnOther)
                    prefs.saveTransportP()
                    prefs.actualizar()
                    porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
                }
                btnShop.setOnClickListener {
                    val temp = ingFin.text.toString().toInt()
                    val cont = prefs.getShop() + temp
                    prefs.saveShop(cont)
                    setInvisible(btnBill,btnHealth,btnTransport,btnShop,btnOut,btnOther)
                    prefs.saveShopP()
                    prefs.actualizar()
                    porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
                }
                btnOut.setOnClickListener {
                    val temp = ingFin.text.toString().toInt()
                    val cont = prefs.getOut() + temp
                    prefs.saveOut(cont)
                    setInvisible(btnBill,btnHealth,btnTransport,btnShop,btnOut,btnOther)
                    prefs.saveOutP()
                    prefs.actualizar()
                    porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
                }
                btnOther.setOnClickListener {
                    val temp = ingFin.text.toString().toInt()
                    val cont = prefs.getOther() + temp
                    prefs.saveOther(cont)
                    setInvisible(btnBill,btnHealth,btnTransport,btnShop,btnOut,btnOther)
                    prefs.saveOtherP()
                    prefs.actualizar()
                    porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
                }
                prefs.actualizar()
            }else{
                egress.text = prefs.getEgr().toString()
                balance.text = prefs.getBal().toString()
                val alerta = AlertDialog.Builder(this)
                    .setTitle("Error!").setMessage("Debes digitar un monto!")
                alerta.show()
            }
        }
        btnWipe.setOnClickListener {
            prefs.cleanData()
            income.text = prefs.getInc().toString()
            egress.text = prefs.getEgr().toString()
            balance.text = prefs.getBal().toString()
            prefs.actualizar()
            porcents.text = "Deudas ${prefs.getBillP()}%/Salud ${prefs.getHealthP()}%/Mercado ${prefs.getShopP()}%/Transporte ${prefs.getTransportP()}%/Salidas ${prefs.getOutP()}%/Otro ${prefs.getOtherP()}%"
        }
    }
    fun setInvisible(btnBill: Button,btnHealth: Button,btnTransport: Button,btnShop: Button,btnOut: Button,btnOther: Button){
        //invisible
        btnBill.visibility = View.INVISIBLE
        btnHealth.visibility = View.INVISIBLE
        btnTransport.visibility = View.INVISIBLE
        btnShop.visibility = View.INVISIBLE
        btnOut.visibility = View.INVISIBLE
        btnOther.visibility = View.INVISIBLE
    }
}