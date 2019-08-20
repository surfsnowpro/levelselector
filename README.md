# Level Selector
A level-meter style control to allow users to select an option within a range. Can be "fully" customizable.  "Fully" specified
given that I'm trying to add as much customizability as possible.
</br></br>
## Usage
So far, there is support for the following attributes:
* selectionStyle - Specifies if the selection is single or multiple, as well as the order of multiple selection
* maxLevels - The number of selectors displayed
* selectedColor - The color of the selected items.  The default is the value specified in your theme for `colorAccent`
* unSelectedColor - The color of the un-selected items. The default is the value specified in your theme for `colorPrimary`
* hasStroke - Specifies whether or not to use a stroke for the items (right now, the stroke color is the selectedColor)
* buttonHeight - The height of each level selection item
* buttonWidth - The width of each level selection item
* buttonSpacing - Spacing between each item (self explanitory)
* buttonRadius - The corner radius of each item
#### Example
```kotlin
val volumeControl = LevelSelector(this).apply {
    buttonHeight = 40
    buttonWidth = 60
    buttonRadius = 17f
}
```
## Installation
``` gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
    
    
dependencies {
    implementation 'com.github.surfsnowpro:levelselector:x.y.z'
}
```

