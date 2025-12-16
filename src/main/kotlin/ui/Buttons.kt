package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import viewmodel.IngestionViewModel

@Composable
fun Buttons(vm: IngestionViewModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth().background(Color(0xFF1a2c22), shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedButton(
                onClick = vm::reset,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Reset")
            }

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = vm::submit,
                enabled = !vm.isRunning,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2ED573),
                    contentColor = Color(0xff102217)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(if (vm.isRunning) "Running..." else "Start", color = Color(0xff102217))
            }
        }
    }


}