package com.example.my

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my.ui.theme.MyTheme
import kotlin.random.Random
import kotlin.random.nextULong

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTheme {
                Scaffold { innerPadding ->
                    SimpleScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun SimpleScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        var count by rememberSaveable { mutableStateOf(0) }
        var color by rememberSaveable { mutableStateOf(Color.Yellow.toArgb()) }

        Container("Button example") {
            Column {
                MyButton(text = "Click me", color = Color(color)) {
                    count += 1
                    color = Color(
                        red = Random.nextFloat(),
                        green = Random.nextFloat(),
                        blue = Random.nextFloat(),
                    ).toArgb()
                }
                Text("count of clicks: $count")
            }
        }

        var textFieldValue by rememberSaveable { mutableStateOf("") }

        Container("TextField example") {
            Column {
                MyTextField(textFieldValue) { textFieldValue = it }
                Text(textFieldValue.ifEmpty { "[empty]" })
            }
        }

        Container("checkboxes example") {
            Column {
                CheckBoxes { selectedItems ->
                    Text(selectedItems)
                }
            }
        }



    }

}
