package com.balqis.sqlite

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.Executors

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
        val db = ColorDatabase.getInstance(this)
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            val colorRed = Color(hexColor = "#FF0000", name = "Red")
            db.ColorDao().insert(colorRed)

            val colors = db.ColorDao().getAll()
            Log.d("Database", "Colors in DB: $colors")
        }
    }
}