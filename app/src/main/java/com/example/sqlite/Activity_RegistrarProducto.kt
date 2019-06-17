package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity__registrar_producto.*

class Activity_RegistrarProducto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__registrar_producto)

        val db = FirebaseFirestore.getInstance()


            btguardarproducto.setOnClickListener {

                val pro=producto()
                pro.nombre=tvnombre.text.toString()
                pro.categoria=tvcategoria.text.toString()
                pro.cantidad=tvcantidad.text.toString().toInt()
                pro.precio=tvprecio.text.toString().toFloat()

                val pro1 = HashMap<String,Any>()
                pro1.put("nombre", tvnombre.text.toString())
                pro1.put("precio",tvprecio.text.toString().toFloat())
                pro1.put("cantidad",tvcantidad.text.toString().toInt())
                pro1.put("categoria", tvcategoria.text.toString())

                db.collection("productos")
                    .add(pro).
                    addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(this,"Producto Insertado",Toast.LENGTH_SHORT).show()
                    })
                    .addOnFailureListener(OnFailureListener {
                        Toast.makeText(this,"Error al insertar",Toast.LENGTH_SHORT).show()
                    })

                    finish()

            }




    }
}
