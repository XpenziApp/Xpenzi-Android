package com.infinity.coins.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val MONTHS =
    listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

@Composable
fun MonthlyCalendar(currentMonth: Int, onMonthSelected: (Int) -> Unit) {
    var selectedMonth by remember { mutableStateOf(currentMonth) }

    LazyRow(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(MONTHS) { index, month ->
            Month(
                month = month,
                selected = index == selectedMonth,
                onMonthSelected = {
                    selectedMonth = it
                    onMonthSelected(selectedMonth)
                }
            )
        }
    }
}

@Composable
private fun Month(month: String, selected: Boolean = false, onMonthSelected: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onMonthSelected(MONTHS.indexOf(month))
                }
                .background(
                    shape = if (selected) RoundedCornerShape(8.dp) else RoundedCornerShape(0.dp),
                    color = if (selected) MaterialTheme.colorScheme.inverseOnSurface else Color.Transparent
                )
                .padding(8.dp),
            text = month,
            style = MaterialTheme.typography.labelSmall,
        )
        if (selected) {
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(2.dp)
                    .background(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewMonthlyCalendar() {
    MonthlyCalendar(
        currentMonth = 2,
        onMonthSelected = {

        }
    )
}