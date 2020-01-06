package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
/* własny widok, który bedzie odpowiedzialny za malowani kółek po ekranie
 Podstawa, musi dziedziczyć po View i przyjmować parametry: Context i AttributeSet?
 w java byłyby dwa konstruktory (context - gdy tworzony jest z poziomu kodu i
 a attr... gdy z pliku xml. Kotlin sam podstawi pod nulla xmla*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
