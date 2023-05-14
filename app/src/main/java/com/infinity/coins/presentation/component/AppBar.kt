package com.infinity.coins.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infinity.coins.presentation.ui.theme.Gray01Light
import com.infinity.coins.presentation.ui.theme.White
import java.util.Calendar

@Composable
@Preview
fun AppBar() {
    Column(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                ambientColor = Gray01Light,
                spotColor = Gray01Light
            )
            .background(color = White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Monthly Expenses",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                modifier = Modifier.clickable {

                },
                imageVector = Icons.Rounded.ArrowDropDown,
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "Select View Type"
            )
        }
        MonthlyCalendar(
            currentMonth = Calendar.getInstance().get(Calendar.MONTH),
            onMonthSelected = {

            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}