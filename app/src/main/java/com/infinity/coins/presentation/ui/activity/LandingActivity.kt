package com.infinity.coins.presentation.ui.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infinity.coins.presentation.component.AppBar
import com.infinity.coins.presentation.component.TransactionHistory
import com.infinity.coins.presentation.ui.theme.BackgroundInterfaceBg
import kotlinx.coroutines.launch


class LandingActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppBar()
                    },
                    content = {
                        TransactionHistory(paddingValues = it)
                    },
                    bottomBar = {
                        //CoinsBottomNavigation(navController = navController)
                    },
                    snackbarHost = {
                        SnackbarHost(snackBarHostState)
                    },
                    floatingActionButton = {
                        AddExpenseButton(snackBarHostState)
                    },
                    containerColor = BackgroundInterfaceBg
                )
            }
        }

        val cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)

        if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
            do {
                var msgData = ""
                for (idx in 0 until cursor.columnCount) {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx)
                }
                Log.d("TEST", msgData)
            } while (cursor.moveToNext())
        } else {
            // empty box, no SMS
        }

        cursor?.close()
    }

    @Composable
    private fun AddExpenseButton(snackBarHostState: SnackbarHostState) {
        val scope = rememberCoroutineScope()
        ExtendedFloatingActionButton(
            modifier = Modifier.padding(16.dp),
            onClick = {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        "Feature coming soon!"
                    )
                }
            },
            text = { Text(text = "Add Expense") },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = ""
                )
            }
        )
    }
}