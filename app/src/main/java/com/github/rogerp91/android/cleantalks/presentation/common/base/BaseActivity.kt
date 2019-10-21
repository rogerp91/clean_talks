package com.github.rogerp91.android.cleantalks.presentation.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.rogerp91.android.cleantalks.module.injectModule

/**
 * Created by rpatino on oct/2019
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModule()
    }
}