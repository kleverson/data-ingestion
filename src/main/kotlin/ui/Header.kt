package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.SplineSans

@Composable
fun Header() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Data Ingestion", style = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = SplineSans,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Configure your source stream and fine-tune processing parameters", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SplineSans,
                color = Color.White
            )
        )
    }
}