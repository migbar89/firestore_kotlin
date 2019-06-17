package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    lateinit var listaproducto: MutableList<DocumentSnapshot>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings=settings


        db.collection("productos").
            addSnapshotListener(EventListener<QuerySnapshot>{snapshots, e ->

                if (e != null) {
                    Log.w("Realtime", "listen:error", e)
                    return@EventListener
                }
                listaproducto= snapshots!!.documents
                //Toast.makeText(this,listaproducto.size.toString(),Toast.LENGTH_SHORT).show()
                cargardatos()

            });



        btinsertar.setOnClickListener {
           var intent =Intent(this,Activity_RegistrarProducto::class.java)
            startActivity(intent)
       }

    }


    fun cargardatos()
    {
        recycleproducto.layoutManager=LinearLayoutManager(applicationContext,LinearLayout.VERTICAL,false)
       // if(listaproducto.size>0) {
            var adapter = adapterproducto(listaproducto)
            recycleproducto.adapter = adapter
        //}
    }
    override fun onRestart() {
        super.onRestart()


    }
}
