package com.example.m203_sipinner_from_arraylist

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide


data class Smartphone (
    val id : Int,
    val nom : String,
    val prix : Double,
    val image : String
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var LS = ArrayList<Smartphone>()
        val s1 = Smartphone(1,"Samsung A15", 3400.0, "https://tabtel.ma/14097-large_default/samsung-galaxy-a15-4g-8go-256go-dual-sim.jpg")
        val s2 = Smartphone(2,"iphone 15 pro", 6400.0, "https://uno.ma/pub/media/catalog/product/cache/af8d7fd2c4634f9c922fba76a4a30c04/l/d/ld0005977505_2.jpeg")

        LS.add(s1)
        LS.add(s2)

        var List2 = ArrayList<String>()

        List2.add("Select a smartphone")

        for(sm in LS) {

            List2.add(sm.nom+" - "+sm.prix)
        }

        val sp = findViewById<Spinner>(R.id.sp)

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, List2)

        // Spécifier le layout à utiliser quand la liste d'options s'affiche
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp.adapter = adapter

        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Récupérer l'élément sélectionné en fonction de sa position
                if (position > 0) {
                    val imgView = findViewById<ImageView>(R.id.imageView)
                    Glide.with(applicationContext)
                        .load(LS[position - 1].image)
                        .into(imgView)

                    val txtV_nom = findViewById<TextView>(R.id.textView3)
                    txtV_nom.text = LS[position - 1].nom

                    val txtV_prix = findViewById<TextView>(R.id.textView4)
                    txtV_prix.text = LS[position - 1].prix.toString() + " MAD"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Gérer le cas où rien n'est sélectionné (optionnel)
                Toast.makeText(this@MainActivity, "Rien sélectionné", Toast.LENGTH_SHORT).show()
            }
        }

    }
}