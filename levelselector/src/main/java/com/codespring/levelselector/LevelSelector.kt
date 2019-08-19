package com.codespring.levelselector

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import java.util.*

class LevelSelector(context: Context) : RelativeLayout(context) {
    private val TAG = "LevelSelector"

    private val buttons = ArrayList<View>()
    var levelSelectionListener: ((index: Int, selectedIndex: Int, max: Int) -> Unit)? = null

    var selectionStyle: Int = MULTIPLE_LEFT_TO_RIGHT
        set(value) {
            field = value
            setButtonListeners()
        }
    var maxLevels = 5
        set(value) {
            field = value
            updateUI()
        }
    var selectedColor = resolveThemeColor(R.attr.colorAccent)
        set(value) {
            field = value
            updateColorLists()
            updateUI()
        }
    var unSelectedColor = resolveThemeColor(R.attr.colorPrimary)
        set(value) {
            field = value
            updateColorLists()
            updateUI()
        }
    var hasStroke: Boolean = false
        set(value) {
            field = value
            updateUI()
        }
    var buttonHeight = 20
        set(value) {
            field = value
            updateUI()
        }
    var buttonWidth = 30
        set(value) {
            field = value
            updateUI()
        }
    var buttonSpacing = 5
        set(value) {
            field = value
            updateUI()
        }
    var buttonRadius = 4f
        set(value) {
            field = value
            updateUI()
        }

    private var selectedIndex = 0
        set(value) {
            field = value
            updateButtonSelections()
        }

    private val states = arrayOf(
        IntArray(1).apply { set(0, android.R.attr.state_selected) },
        IntArray(1)
    )

    private lateinit var fillColors: IntArray
    private lateinit var strokeColors: IntArray
    private lateinit var colorStateList: ColorStateList
    private lateinit var strokeColorStateList: ColorStateList

    init {
        updateColorLists()
        updateUI()
    }

    private fun resolveThemeColor(attrId: Int) : Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attrId, typedValue, true)
        return typedValue.data
    }

    private fun updateColorLists() {
        val transparent = ContextCompat.getColor(context, android.R.color.transparent)
        fillColors = IntArray(2).apply {
            set(0, selectedColor)
            set(1, unSelectedColor)
        }
        strokeColors = IntArray(2).apply {
            set(0, transparent)
            set(1, selectedColor)
        }
        colorStateList = ColorStateList(states, fillColors)
        strokeColorStateList = ColorStateList(states, strokeColors)
    }

    private fun updateUI() {
        buttons.clear()
        removeAllViews()

        for (i in 0 until maxLevels) {
            val bg = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = buttonRadius
                color = colorStateList
                if (hasStroke) {
                    setStroke(1, strokeColorStateList)
                }
            }

            val view = View(context).apply {
                id = i
                background = bg
                layoutParams = LayoutParams(buttonWidth, buttonHeight).apply {
                    if (i > 0) {
                        setMargins(i * (buttonSpacing + buttonWidth), 0, 0, 0)
                    }
                }
            }
            buttons.add(view)
            addView(view)
        }
        setButtonListeners()
    }

    private fun setButtonListeners() {
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (selectionStyle) {
                    SINGLE -> selectedIndex = if (selectedIndex == index) -1 else index
                    MULTIPLE_LEFT_TO_RIGHT -> selectedIndex = if (selectedIndex == index) index - 1 else index
                    MULTIPLE_RIGHT_TO_LEFT -> selectedIndex = if (selectedIndex == index) index + 1 else index
                }
                levelSelectionListener?.invoke(index, selectedIndex, maxLevels)
            }
        }
    }

    private fun updateButtonSelections() {
        buttons.forEachIndexed { index, button ->
            when (selectionStyle) {
                SINGLE -> button.isSelected = index == selectedIndex
                MULTIPLE_LEFT_TO_RIGHT -> button.isSelected = index <= selectedIndex
                MULTIPLE_RIGHT_TO_LEFT -> button.isSelected = index >= selectedIndex
            }
        }
    }

    companion object {
        const val MULTIPLE_LEFT_TO_RIGHT = 10
        const val MULTIPLE_RIGHT_TO_LEFT = 11
        const val SINGLE = 12
    }
}