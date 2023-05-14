package com.infinity.coins.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infinity.coins.presentation.ui.theme.FoundationsAccentGreen
import com.infinity.coins.presentation.ui.theme.SeparatorDark
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun TransactionHistory(paddingValues: PaddingValues) {
    val chartEntryModel = entryModelOf(4f, 12f, 8f, 16f)
    LazyColumn(
        modifier = Modifier
            .padding(
                top = 16.dp + paddingValues.calculateTopPadding(),
                bottom = 16.dp + paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ProvideChartStyle(
                chartStyle = m3ChartStyle(
                    axisGuidelineColor = SeparatorDark,
                    axisLineColor = FoundationsAccentGreen
                )
            ) {
                Chart(
                    chart = lineChart(),
                    model = chartEntryModel,
                    startAxis = startAxis(),
                    bottomAxis = bottomAxis()
                )
            }
        }
        items(15) {
            TransactionCard()
        }
    }
}