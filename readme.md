# Webhook Reprocessing Desktop App

Desktop application built with Jetpack Compose to process and reprocess webhook data stored as JSON files.

## Problem
Due to a production bug, a system stopped processing webhook events for several days, resulting in lost records. The webhook payloads were later recovered as JSON files, but required a reliable way to be reprocessed.

## Solution
This application was created to batch process webhook JSON files, allowing safe reprocessing of missed events and ensuring data consistency.

## Features
- Batch processing of webhook JSON files
- Desktop application built with Jetpack Compose
- Error handling and processing logs
- Designed for reliability and data recovery scenarios

## Tech Stack
- Kotlin
- Jetpack Compose (Desktop)
- JSON Processing

## How to Run
```./gradlew run```

## Use Case
Ideal for data recovery, data reprocessing, and operational support scenarios.
