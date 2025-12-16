package core

import java.io.File

object FileLogger {

    private val logDir = File(System.getProperty("user.home"), ".runnableapi")
    private val logFile = File(logDir, "ingestion.log")

    init {
        if (!logDir.exists()) logDir.mkdirs()
        if (!logFile.exists()) logFile.createNewFile()
    }

    fun write(message: String) {
        logFile.appendText(message + "\n")
    }

    fun read(): List<String> =
        if (logFile.exists()) logFile.readLines() else emptyList()

    fun clear() {
        logFile.writeText("")
    }

    fun path(): String = logFile.absolutePath
}
