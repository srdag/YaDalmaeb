package com.example.my

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckBoxItem(
    val title: String,
    val isChecked: Boolean = false
): Parcelable


@Composable
fun CheckBoxes(
    text: @Composable (String) -> Unit
) {
    var checkBoxItems by rememberSaveable {
        mutableStateOf(List(6) { index ->
            CheckBoxItem("checkbox ${index + 1}")
        })
    }


    Column {
        checkBoxItems.forEach { checkBox ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = checkBox.isChecked,
                    onCheckedChange = { checked ->
                        checkBoxItems = checkBoxItems.map {
                            if (it.title == checkBox.title) it.copy(isChecked = checked) else it
                        }
                    }
                )
                Text(checkBox.title)
            }
        }

        val selectedItems = "selected " + checkBoxItems
            .filter { it.isChecked }
            .joinToString { it.title }

        text(selectedItems)
    }

}
