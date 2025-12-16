import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.ui.Modifier

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import core.SplineSans
import ui.Buttons
import ui.FormScreen
import ui.LogPanel
import viewmodel.IngestionViewModel
import java.io.File


@Composable
@Preview
fun App() {

    val vm = remember { IngestionViewModel() }

    MaterialTheme(
        typography = Typography(defaultFontFamily = SplineSans)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(bottom = 16.dp).background(Color(0xFF102217)).padding(32.dp)) {
            FormScreen(vm)

            Buttons(vm, Modifier.align(Alignment.BottomCenter))
        }
    }
}


fun main() = application {
    Window(
        title = "Data ingestion",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
