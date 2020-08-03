package com.moanes.wisysttask.utils

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.moanes.wisysttask.R

class ProgressDialog(context: Context) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_progress_dialog)
    }
}