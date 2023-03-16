package com.zeroninedev.core_compose.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize

/**
 * Radio button with text
 *
 * @param list list of pair key and items
 * @param selectedItem selected item
 * @param modifier modifier
 * @param onOptionSelect callback on click with key
 */
@Composable
fun RadioButtonComponent(
    list: List<Pair<Int, String>>,
    selectedItem: String,
    modifier: Modifier = Modifier,
    onOptionSelect: (Int) -> Unit
) {
    var selectedOption by remember { mutableStateOf(selectedItem) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        list.forEach { item ->
            val text = item.second
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            selectedOption = text
                            onOptionSelect(item.first)
                        }
                    )
                    .padding(horizontal = MediumSize)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    modifier = Modifier.padding(SmallSize),
                    onClick = null
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = MediumSize)
                )
            }
        }
    }
}