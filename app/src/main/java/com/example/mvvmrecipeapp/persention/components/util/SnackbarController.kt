package com.example.mvvmrecipeapp.persention.components.util

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * If a snackbar is visible and the user triggers a second snackbar to show, it will remove
 * the first one and show the second. Likewise with a third, fourth, ect...
 *
 * If a mechanism like this is not used, snackbar GetRecipeEvent added to the Scaffolds "queue", and will
 * show one after another. I don't like that.
 *
 */
class SnackbarController(
    private val scope: CoroutineScope,
) {

    private var snackbarJob: Job? = null

    init {
        cancelActiveJob()
    }

    fun getScope() = scope

    fun showSnackbar(
        message: String,
        actionLabel: String,
        snackbarHostState: SnackbarHostState,
        context: Context
    ) {
        cancelActiveJob()
        snackbarJob = scope.launch {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                duration = SnackbarDuration.Short
            )
            actionsHandler(context,snackbarResult)
            cancelActiveJob()
        }
    }

    private suspend fun actionsHandler(context: Context,snackbarResult:SnackbarResult){
        when (snackbarResult) {
            SnackbarResult.Dismissed -> {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Dismissed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            SnackbarResult.ActionPerformed -> {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "ActionPerformed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun cancelActiveJob() {
        snackbarJob?.let { job ->
            job.cancel()
            snackbarJob = Job()
        }
    }
}