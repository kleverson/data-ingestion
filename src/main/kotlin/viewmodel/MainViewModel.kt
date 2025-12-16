package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.FileLogger
import data.LogEntry
import data.LogLevel
import data.TimeLogItem
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import remote.sendToApi
import java.io.File
import kotlin.math.ceil

class IngestionViewModel{
    val logs = mutableStateListOf<LogEntry>()

    var endpointUrl by mutableStateOf("")
    var delay by mutableStateOf("300")
    var chunckSize by mutableStateOf("100")
    var batchSize by mutableStateOf("10")
    var filePath by mutableStateOf<String?>(null)

    var isRunning by mutableStateOf(false)
    var progress by mutableStateOf(0f)

    var currentChunk by mutableStateOf(0)
    var currentItem by mutableStateOf(0)
    var totalChunks by mutableStateOf(0)

    var status by mutableStateOf("Idle")

    private var job: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        FileLogger.read().forEach {
            logs.add(
                LogEntry(it, LogLevel.INFO)
            )
        }
    }


    fun pickFile(){
        val dialog = java.awt.FileDialog(null as java.awt.Frame?, "Select JSON file", java.awt.FileDialog.LOAD)
        dialog.isVisible = true

        dialog.file?.let {
            filePath = dialog.directory + it
        }
    }

    fun submit(){
        if(filePath == null) return

        job?.cancel()

        isRunning = true
        progress = 0f
        currentChunk = 0
        currentItem = 0
        status = "Running"

        val items = readJsonFile(filePath!!)
        val chunck = chunckSize.toIntOrNull() ?: 100
        totalChunks = ceil(items.size / chunck.toDouble()).toInt()

        log("Starting ingestion of ${items.size} items in $totalChunks chunks.", LogLevel.INFO)

        job = scope.launch {
            items.chunked(chunck).forEachIndexed{chunkIndex, chuncItens->
                currentChunk = chunkIndex +1

                log("Processing chunk ${chunkIndex +1} / $totalChunks", LogLevel.INFO)

                chuncItens.forEachIndexed{itemIndex, _ ->
                    currentItem = itemIndex +1
                    val sliceContent = chuncItens.slice(
                        itemIndex until
                                (itemIndex + (batchSize.toIntOrNull() ?: 10))
                                    .coerceAtMost(chuncItens.size)
                    )

                    val result = sendToApi(
                        endpointUrl = endpointUrl,
                        body = sliceContent,
                    )
                    delay(delay.toLong())
                    log("Sent batch of ${sliceContent.size} items to API. Status code is ${result}", LogLevel.INFO)
                    progress = ((chunkIndex * chunck) + itemIndex +1).toFloat() / items.size
                }

                delay(delay.toLong())
            }

            log("Ingestion completed successfully.", LogLevel.SUCCESS)

            withContext(Dispatchers.Default){
                isRunning = false
            }
        }


    }

    private fun log(message: String, level: LogLevel = LogLevel.INFO){
        val entry = LogEntry(message, level)
        logs.add(entry)

        FileLogger.write(formatLog(entry))
    }


    fun reset() {
        job?.cancel()
        status ="Idle"
        endpointUrl = ""
        delay = "300"
        chunckSize = "100"
        batchSize = "10"
        filePath = null
        isRunning = false
        progress = 0f
        currentChunk = 0
        currentItem = 0
        totalChunks = 0
    }

}


private fun formatLog(log: LogEntry): String {
    val time = java.time.LocalDateTime.now()
    return "[${time}] [${log.level}] ${log.message}"
}

fun readJsonFile(path: String): List<TimeLogItem>{
    val content = File(path).readText()

    return Json{
        ignoreUnknownKeys = true
    }.decodeFromString(content)
}