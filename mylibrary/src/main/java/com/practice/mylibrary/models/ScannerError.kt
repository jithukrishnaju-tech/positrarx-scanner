package com.practice.mylibrary.models

sealed class ScannerError {
    object CameraPermissionDenied : ScannerError()
    object CameraInitializationError : ScannerError()
    data class ProcessingError(val message: String) : ScannerError()
}