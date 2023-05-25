package com.painrate.modules.patients.ui

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PatientDatabaseKt(val context: Context): SQLiteOpenHelper(context, "PATIENTS", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_table.patients)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists patients")
    }

    fun saveuserdata(name: String, phone: String, notes: String, location: String, dob: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("phone", phone)
        cv.put("notes", notes)
        cv.put("location", location)
        cv.put("dob", dob)
        val result = db.insert("PATIENTS", null, cv)
        if (result == -1.toLong()){
            return false
        }
        return true
    }

    fun updateuserdata(name: String, phone: String, notes: String, location: String, dob: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("phone", phone)
        cv.put("notes", notes)
        cv.put("location", location)
        cv.put("dob", dob)

        val cursor: Cursor = db.rawQuery("select * from PATIENTS where name = ?", arrayOf(name))
        if(cursor.count>0){
            val result = db.update("PATIENTS", cv, "name=?", arrayOf(name))
            return result != -1
        }
        else{
            return false
        }
    }

    fun deleteuserdata(name: String): Boolean {
        val db = this.writableDatabase

        val cursor: Cursor = db.rawQuery("select * from PATIENTS where name = ?", arrayOf(name))

        if(cursor.count>0){
            val result = db.delete("PATIENTS","name=?", arrayOf(name))
            return result != -1
        }
        else{
            return false
        }
    }

    fun gettext(): Cursor? {
        val db = this.writableDatabase
        val cursor = db.rawQuery("select * from Patients", null)
        return cursor
    }

//    fun execQuery(sql:String):Boolean{
//        try{
//            val database = this.writableDatabase
//            database.execSQL(sql)
//
//        }catch (e:Exception){
//             e.printStackTrace()
//            return false
//        }
//
//        return true
//    }
//
//    @SuppressLint("Range")
//    fun getData(){
//        var cursor: Cursor?=null
//        try {
//            val database = this.readableDatabase
//            cursor = database.rawQuery("select NAME from PATIENTS", null)
//            cursor?.moveToFirst()
//            do{
//                var name = cursor.getString(cursor.getColumnIndex("NAME"))
//                Log.d("Patient", "Name $name")
//            }while(cursor.moveToNext())
//        }catch (e:java.lang.Exception){
//            e.printStackTrace()
//        }finally {
//            cursor?.close()
//        }
//
//    }

    companion object{
        private const val DB_VERSION = 1
        private const val DB_NAME = "Patients.DB"

    }

}