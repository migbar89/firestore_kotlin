package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestoreSettings




class MainActivity : AppCompatActivity() {


    var listaproducto=ArrayList<producto>()
    //var db : room? = null



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
               listaproducto.clear()
                if (e != null) {
                    Log.w("Realtime", "listen:error", e)
                    return@EventListener
                }
                for (doc in snapshots!!)
                {
                    val p = doc.toObject(producto::class.java)
                    listaproducto.add(p)

                }

                cargardatos()

            });
   /*  //Cargar datos de firestore
        db.collection("productos")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    listaproducto.clear()
                    for (document in task.result!!) {
                         val p = document.toObject(producto::class.java)
                        listaproducto.add(p)
                    }
                    cargardatos()


                } else {
                   // Log.w(FragmentActivity.TAG, "Error getting documents.", task.exception)
                }
            }

*/






    btcargar.setOnClickListener {

      /*  db.collection("productos")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    listaproducto.clear()
                    for (document in task.result!!) {
                        val p = document.toObject(producto::class.java)
                        listaproducto.add(p)
                    }
                    cargardatos()


                } else {
                    // Log.w(FragmentActivity.TAG, "Error getting documents.", task.exception)
                }
            }*/

       // Toast.makeText(applicationContext,datospublicos.consultaproductos(applicationContext).size.toString(),Toast.LENGTH_SHORT).show()


    }


        btinsertar.setOnClickListener {
           var intent =Intent(this,Activity_RegistrarProducto::class.java)
            startActivity(intent)
       }









    }

    fun insertar()
    {

    }

    fun cargardatos()
    {
        recycleproducto.layoutManager=LinearLayoutManager(applicationContext,LinearLayout.VERTICAL,false)
        if(listaproducto.size>0) {
            var adapter = adapterproducto(listaproducto)
            recycleproducto.adapter = adapter
        }
    }
    override fun onRestart() {
        super.onRestart()


    }
}
