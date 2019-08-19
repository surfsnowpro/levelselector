package com.codespring.levelselectorexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codespring.levelselector.LevelSelector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val volumeControl = LevelSelector(this).apply {
            maxLevels = 6
            buttonHeight = 40
            buttonWidth = 40
            buttonRadius = 40f
        }
        volumeControl.levelSelectionListener = { index, selectedIndex, max ->
            Log.d(TAG, "Clicked item $index. Current selection is $selectedIndex or $max")
        }
        main_content.addView(volumeControl)
    }
}
