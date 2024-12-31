package com.practice.mylibrary.models

import com.practice.mylibrary.models.BarcodeFormat

data class ScanResult(
    val rawValue: String,
    val format: BarcodeFormat,
    val metadata: Map<String, Any> = emptyMap(),
    val timestamp: Long = System.currentTimeMillis()
)
