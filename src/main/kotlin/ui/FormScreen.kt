package ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.IngestionViewModel


@Composable
fun FormScreen(vm: IngestionViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Form(vm)
        }


        Spacer(modifier = Modifier.height(24.dp))

        StatusPanel(vm)

        Spacer(modifier = Modifier.height(24.dp))

    }
}

