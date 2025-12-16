package viewmodel

data class SettingsData(
    var endpintUrl: String,
    var filePath: String,
    var delay: Int,
    var chunckSize: Int,
    var batchSize: Int
)
