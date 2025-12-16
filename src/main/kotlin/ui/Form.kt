package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viewmodel.IngestionViewModel
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun Form(vm: IngestionViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f).background(Color(0xFF1a2c22), shape = RoundedCornerShape(16.dp))
                .padding(16.dp),

            ) {
            Row() {
                Icon(Icons.Default.Settings, contentDescription = null, tint = Color(0xFF2ED573))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Source Configuration", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            FormField(
                label = "Endpoint url",
                value = vm.endpointUrl,
                icon = Icons.Default.Link,
                onValueChange = { vm.endpointUrl = it },
                modifier = Modifier.fillMaxWidth(),
                onlyNumber = false
            )
            Spacer(Modifier.height(8.dp))


            Column(
                modifier = Modifier.height(180.dp)
                    .border(color = Color.White, width = 1.dp, shape = RoundedCornerShape(16.dp)).fillMaxWidth()
                    .clickable {
                        vm.pickFile()
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = Modifier.background(Color.Transparent),
                    onClick = {
                        vm.pickFile()
                    }
                ) {
                    Icon(
                        Icons.Default.UploadFile,
                        contentDescription = "Select JSON File",
                        tint = Color(0xFF2ED573)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                if(vm.filePath.isNullOrEmpty()) {
                    Text(" Select JSON File", style = TextStyle(color = Color.White))
                }else{
                    vm.filePath?.let {
                        Text("File Path: $it", style = TextStyle(color = Color.White, fontSize = 12.sp))
                    }
                }
            }


        }

        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier.weight(1f).background(Color(0xFF1a2c22), shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {

            FormField(
                label = "Processing Delay",
                value = vm.delay,
                suffix = "ms",
                icon = Icons.Default.Timer,
                onValueChange = { vm.delay = it },
                modifier = Modifier.fillMaxWidth(),
                onlyNumber = true
            )
            Spacer(Modifier.width(8.dp))
            FormField(
                label = "Chunk Size",
                value = vm.chunckSize,
                icon = Icons.Default.TableView,
                suffix = "ms",
                onValueChange = { vm.chunckSize = it },
                modifier = Modifier.fillMaxWidth(),
                onlyNumber = true
            )
            Spacer(Modifier.width(8.dp))
            FormField(
                label = "Batch Size",
                value = vm.batchSize,
                suffix = "ms",
                icon = Icons.Default.SelectAll,
                onValueChange = { vm.batchSize = it },
                modifier = Modifier.fillMaxWidth(),
                onlyNumber = true
            )
        }
    }
}