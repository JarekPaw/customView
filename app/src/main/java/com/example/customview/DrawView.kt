package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.ArrayList

/* własny widok, który bedzie odpowiedzialny za malowani kółek po ekranie
 Podstawa, musi dziedziczyć po View i przyjmować parametry: Context i AttributeSet?
 w java byłyby dwa konstruktory (context - gdy tworzony jest z poziomu kodu i
 a attr... gdy z pliku xml. Kotlin sam podstawi pod nulla xmla*/

class DrawView(context: Context, attrs: AttributeSet? = null): View (context, attrs){
/* lateinit var to zmienna, ktora będzie uzupełniona w przyszłości
 tworzymy miejsce na obiekt, który stworzymy w przyszłości
 arrayLista circles będzie przechowywać wszystkie obiekty
    */
    private lateinit var circle: Circle
    val circles = ArrayList<Circle>()

    // dla nadpisanej metody onDraw() będzie przechowywać atrybuty naszych obiektów, np: kolor
    private var paint = Paint()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!= null){
            when(event.action){
/* action down - naciśnięcie, move - ruszamy palcem po ekranie, up - puszczamy palec,
 cancel - dotyk został przerwany, anulowany
    */
                // tworzenie kółek
                MotionEvent.ACTION_DOWN -> {
                    val originPointF1 = PointF(event.x, event.y)
                    circle = Circle()
                    circles.add(circle)
                    circle.orginPoinF = originPointF1
                }
                MotionEvent.ACTION_UP -> {
                    val currentPointF1 = PointF(event.x, event.y)
                    circle.currentPointF = currentPointF1
                    invalidate() //onDraw jeszcze raz przerzuci widoki, wtedy już arraylista nie będzie pusta!
                }

            }
        }
        return true // trzeba zmienić na true
    }

    override fun onDraw(canvas: Canvas?) {
        paint.color = Color.GREEN

        //pobieramy kolka z listy i obliczamy jego promień (wzór: dł.odcinka =
        // pierwiastek z ((x2-x1)2 + (y2-y1)2)
        for (circle: Circle in circles){
            var radius = Math.sqrt(Math.pow(circle.orginPoinF.x - circle.currentPointF.x.toDouble(), 2.0) +
                    Math.pow(circle.orginPoinF.y - circle.currentPointF.y.toDouble(), 2.0))
            canvas?.drawCircle(circle.orginPoinF.x, circle.orginPoinF.y, radius.toFloat(), paint)
        }

        super.onDraw(canvas)
    }
    // w activity w xml trzeba ustawić widok

}