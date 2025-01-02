package com.practice.mylibrary.models

import android.os.Bundle
import com.practice.mylibrary.models.ScannerConfigBuilder.Companion.EXTRA_ENABLED_FORMATS
import com.practice.mylibrary.models.ScannerConfigBuilder.Companion.EXTRA_ENABLE_BEEP
import com.practice.mylibrary.models.ScannerConfigBuilder.Companion.EXTRA_GUIDE_TEXT
import com.practice.mylibrary.models.ScannerConfigBuilder.Companion.EXTRA_LOCK_ORIENTATION
import com.practice.mylibrary.models.ScannerConfigBuilder.Companion.EXTRA_POWERED_BY_TEXT

data class ScannerConfig(
    val poweredByText: String? = null,
    val guideText: String? = null,
    val enabledFormats: List<BarcodeFormat> = listOf(),
    val enableBeepSound: Boolean = true,
    val lockOrientation: Boolean = false
) {
    companion object {
        fun fromBundle(bundle: Bundle?): ScannerConfig {
            return bundle?.let {
                ScannerConfig(
                    poweredByText = it.getString(EXTRA_POWERED_BY_TEXT),
                    guideText = it.getString(EXTRA_GUIDE_TEXT),
                    enabledFormats = it.getSerializable(EXTRA_ENABLED_FORMATS) as? List<BarcodeFormat> ?: listOf(),
                    enableBeepSound = it.getBoolean(EXTRA_ENABLE_BEEP, true),
                    lockOrientation = it.getBoolean(EXTRA_LOCK_ORIENTATION, false)
                )
            } ?: ScannerConfig()
        }
    }
}
