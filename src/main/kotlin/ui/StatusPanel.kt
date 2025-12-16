package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.IngestionViewModel

@Composable
fun StatusPanel(vm: IngestionViewModel) {
    if (!vm.isRunning && vm.status == "Idle") return

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(Modifier.padding(16.dp)) {

            Text("Status: ${vm.status}")
            Spacer(Modifier.height(8.dp))

            Text("Chunk: ${vm.currentChunk} / ${vm.totalChunks}")
            Text("Item no chunk: ${vm.currentItem}")

            val progress =
                if (vm.totalChunks == 0) 0f
                else vm.currentChunk / vm.totalChunks.toFloat()

            Spacer(Modifier.height(8.dp))

            LinearProgressIndicator(progress = progress)
        }

    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        LogPanel(vm)
    }
}