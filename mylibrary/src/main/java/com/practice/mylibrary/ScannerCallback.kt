package com.practice.mylibrary

import com.practice.mylibrary.models.ScanResult
import com.practice.mylibrary.models.ScannerError

interface ScannerCallback {
    fun onCodeScanned(result: ScanResult)
    fun onError(error: ScannerError)
}