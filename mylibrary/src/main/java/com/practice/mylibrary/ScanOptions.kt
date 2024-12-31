package com.practice.mylibrary

data class ScanOptions(
    val prompt: String = "Scan a barcode",
    val torchEnabled: Boolean = false,
    val orientationLocked: Boolean = true,
)