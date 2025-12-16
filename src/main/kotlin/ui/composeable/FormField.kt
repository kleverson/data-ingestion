package ui.composeable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FormField(
    label: String,
    value: String,
    suffix: String? = null,
    icon: ImageVector? = null,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    onlyNumber: Boolean = true
) {
    Column(modifier = modifier) {

        Text(
            text = label,
            color = Color(0xFF9FB8A7),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = {
                if (!onlyNumber || it.all(Char::isDigit) || it.isEmpty()) {
                    onValueChange(it)
                }

            },
            readOnly = readOnly,
            leadingIcon = icon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = Color(0xFF9FB8A7)
                    )
                }
            },
            trailingIcon = suffix?.let {
                {
                    Text(
                        text = it,
                        color = Color(0xFF2ED573),
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFF102217),
                focusedBorderColor = Color(0xFF2ED573),
                unfocusedBorderColor = Color(0xFF1F3A2B),
                textColor = Color.White,
                cursorColor = Color(0xFF2ED573)
            ),
            modifier = modifier.fillMaxWidth()
        )
    }
}
