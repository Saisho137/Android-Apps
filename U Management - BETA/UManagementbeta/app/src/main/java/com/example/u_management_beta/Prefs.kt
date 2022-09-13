package com.example.u_management_beta

import android.content.Context

class Prefs(val context:Context) {

    val SHARED_NAME = "dtb"
    val SHARED_INC = "Income"
    val SHARED_EGR = "Egress"
    val SHARED_BAL = "Balance"
    //
    val SHARED_BILL = "Deuda"
    val SHARED_TRANSPORT = "Transporte"
    val SHARED_HEALTH = "Salud"
    val SHARED_SHOP = "Mercar"
    val SHARED_OUT = "Salida"
    val SHARED_OTHER = "Otros"
    //
    val SHARED_BILLP = "Deuda"
    val SHARED_TRANSPORTP = "Transporte"
    val SHARED_HEALTHP = "Salud"
    val SHARED_SHOPP = "Mercar"
    val SHARED_OUTP = "Salida"
    val SHARED_OTHERP = "Otros"

    val storage = context.getSharedPreferences(SHARED_NAME,0)

    fun cleanData(){
        saveInc(0)
        saveEgr(0)
        saveBal(0)
        saveBill(0.0F)
        saveHealth(0.0F)
        saveTransport(0.0F)
        saveShop(0.0F)
        saveOut(0.0F)
        saveOther(0.0F)
        saveBillP()
        saveHealthP()
        saveTransportP()
        saveShopP()
        saveOutP()
        saveOtherP()
    }
    //---------------------------------------------------Deuda,Transporte,Salud,Mercado,Salida
    fun saveInc(Income: Int){
        storage.edit().putInt(SHARED_INC,Income).apply()
    }
    fun saveEgr(Egress: Int){
        storage.edit().putInt(SHARED_EGR,Egress).apply()
    }
    fun saveBal(Balance: Int){
        storage.edit().putInt(SHARED_BAL,Balance).apply()
    }
    fun saveBill(Porcentaje: Float){
        storage.edit().putFloat(SHARED_BILL,Porcentaje).apply()
    }
    fun saveTransport(Porcentaje: Float){
        storage.edit().putFloat(SHARED_TRANSPORT,Porcentaje).apply()
    }
    fun saveHealth(Porcentaje: Float){
        storage.edit().putFloat(SHARED_HEALTH,Porcentaje).apply()
    }
    fun saveShop(Porcentaje: Float){
        storage.edit().putFloat(SHARED_SHOP,Porcentaje).apply()
    }
    fun saveOut(Porcentaje: Float){
        storage.edit().putFloat(SHARED_OUT,Porcentaje).apply()
    }
    fun saveOther(Porcentaje: Float){
        storage.edit().putFloat(SHARED_OTHER,Porcentaje).apply()
    }
    //Porcentajes
    fun saveBillP(){
        val porcentaje = (100*getBill())/getEgr()
        storage.edit().putFloat(SHARED_BILL,porcentaje).apply()
    }
    fun saveTransportP(){
        val porcentaje = (100*getTransport())/getEgr()
        storage.edit().putFloat(SHARED_TRANSPORT,porcentaje).apply()
    }
    fun saveHealthP(){
        val porcentaje = (100*getHealth())/getEgr()
        storage.edit().putFloat(SHARED_HEALTH,porcentaje).apply()
    }
    fun saveShopP(){
        val porcentaje = (100*getShop())/getEgr()
        storage.edit().putFloat(SHARED_SHOP,porcentaje).apply()
    }
    fun saveOtherP(){
        val porcentaje = (100*getOther())/getEgr()
        storage.edit().putFloat(SHARED_OTHER,porcentaje).apply()
    }
    fun saveOutP(){
        val porcentaje = (100*getOut())/getEgr()
        storage.edit().putFloat(SHARED_OUT,porcentaje).apply()
    }
    fun actualizar(){
        saveBillP()
        saveHealthP()
        saveTransportP()
        saveShopP()
        saveOutP()
        saveOtherP()
    }
    //---------------------------------------------------Deuda,Transporte,Salud,Mercado,Salida
    fun getInc():Int{
        return storage.getInt(SHARED_INC,0)
    }
    fun getEgr():Int{
        return storage.getInt(SHARED_EGR,0)
    }
    fun getBal():Int{
        return storage.getInt(SHARED_BAL,0)
    }
    fun getBill():Float{
        return storage.getFloat(SHARED_BILL, 0.0F)
    }
    fun getBillP():Float{
        return storage.getFloat(SHARED_BILLP, 0.0F)
    }
    fun getTransport():Float{
        return storage.getFloat(SHARED_TRANSPORT, 0.0F)
    }
    fun getTransportP():Float{
        return storage.getFloat(SHARED_TRANSPORTP, 0.0F)
    }
    fun getHealth():Float{
        return storage.getFloat(SHARED_HEALTH, 0.0F)
    }
    fun getHealthP():Float{
        return storage.getFloat(SHARED_HEALTHP, 0.0F)
    }
    fun getShop():Float{
        return storage.getFloat(SHARED_SHOP, 0.0F)
    }
    fun getShopP():Float{
        return storage.getFloat(SHARED_SHOPP, 0.0F)
    }
    fun getOut():Float{
        return storage.getFloat(SHARED_OUT, 0.0F)
    }
    fun getOutP():Float{
        return storage.getFloat(SHARED_OUTP, 0.0F)
    }
    fun getOther():Float{
        return storage.getFloat(SHARED_OTHER, 0.0F)
    }
    fun getOtherP():Float{
        return storage.getFloat(SHARED_OTHERP, 0.0F)
    }
}