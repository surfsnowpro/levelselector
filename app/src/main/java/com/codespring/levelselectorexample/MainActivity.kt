package com.codespring.levelselectorexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codespring.levelselector.LevelSelector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val volumeControl = LevelSelector(this).apply {
            buttonHeight = 40
            buttonWidth = 60
            buttonRadius = 17f
        }
        main_content.addView(volumeControl)
    }
}
