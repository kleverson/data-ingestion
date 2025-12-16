package data

import kotlinx.serialization.Serializable


@Serializable
data class TimeLogItem(
    val addedResponsibles: List<String>,
    val taskId: String,
    val webhookId: String,
    val eventAuthorId: String,
    val eventType: String,
    val lastUpdatedDate: String
);
