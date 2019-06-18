package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity__registrar_producto.*

class EditarProducto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__registrar_producto)
        val db = FirebaseFirestore.getInstance()

        tvnombre.setText(datospublicos.producto!!.get("nombre").toString())
        tvprecio.setText(datospublicos.producto!!.get("precio").toString())
        tvcantidad.setText(datospublicos.producto!!.get("cantidad").toString())
        tvcategoria.setText(datospublicos.producto!!.get("categoria").toString())

        btguardarproducto.setOnClickListener {

            val pro1 = HashMap<String,Any>()
            pro1.put("nombre", tvnombre.text.toString())
            pro1.put("precio",tvprecio.text.toString().toFloat())
            pro1.put("cantidad",tvcantidad.text.toString().toInt())
            pro1.put("categoria", tvcategoria.text.toString())

          //  datospublicos.producto
            db.collection("productos").document(datospublicos.idproducto.toString()).update(pro1)
                . addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(this,"Producto Insertado", Toast.LENGTH_SHORT).show()
                    })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(this,"Error al insertar", Toast.LENGTH_SHORT).show()
                })

            finish()

        }

    }
}
