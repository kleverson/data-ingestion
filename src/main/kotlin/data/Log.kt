package data

data class LogEntry(
    val message: String,
    val level: LogLevel = LogLevel.INFO,
    val timestamp: Long = System.currentTimeMillis()
)

enum class LogLevel {
    INFO, ERROR, SUCCESS
}
