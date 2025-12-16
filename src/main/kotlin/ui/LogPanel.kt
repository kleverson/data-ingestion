package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.LogEntry
import data.LogLevel
import viewmodel.IngestionViewModel

@Composable
fun LogPanel(vm: IngestionViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFF0F1F17))
            .padding(8.dp)
    ) {
        Text("Logs", color = Color.White, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                vm.logs.forEach { log ->
                    Text(
                        text = formatLog(log),
                        color = when (log.level) {
                            LogLevel.INFO-> Color(0xFF9FB8A7)
                            LogLevel.ERROR -> Color.Red
                            LogLevel.SUCCESS -> Color(0xFF2ED573)
                        },
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

private fun formatLog(log: LogEntry): String {
    val time = java.time.Instant
        .ofEpochMilli(log.timestamp)
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalTime()
        .toString()

    return "[$time] ${log.message}"
}