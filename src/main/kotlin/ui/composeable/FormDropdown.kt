package ui.composeable

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormDropdown(
    label: String,
    value: String,
    icon: ImageVector,
    itens: List<String>,
    onItemSelected:(String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        FormField(
            value = value,
            onValueChange = {},
            label = label,
            icon = icon,
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itens.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}