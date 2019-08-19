package com.codespring.levelselectorexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codespring.levelselector.LevelSelector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val volumeControl = LevelSelector(this).apply {
            maxLevels = 6
            buttonHeight = 40
            buttonWidth = 40
            buttonRadius = 40f
        }
        main_content.addView(volumeControl)
    }
}
