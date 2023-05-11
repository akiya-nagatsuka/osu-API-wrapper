package com.github.nagatsukaakiya.osuapi.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.github.nagatsukaakiya.osuapi.auth.TokenProvider
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val compareViewModel: CompareViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.data?.getQueryParameter("code")?.let {
            val tokenProvider: TokenProvider by inject()
            tokenProvider.setCode(it)
        }

        setContent {
            var text by remember { mutableStateOf("") }

            val myScores by compareViewModel.myTopPPScores.collectAsState()

            Column {
                Button(onClick = {
                    text = "Loading..."
                    compareViewModel.getMyTopPPScores()
                }) {
                    Text("Send message")
                }
                val chartEntryModel = remember(myScores) {
                    entryModelOf(myScores.withIndex().map { FloatEntry(it.index.toFloat(), it.value) })
                }
                Chart(
                    chart = lineChart(
                        pointPosition = LineChart.PointPosition.Start,
                        spacing = 1.dp
                    ),
                    model = chartEntryModel,
                    startAxis = startAxis(),
                    bottomAxis = bottomAxis(),
                )
                Text(myScores.toString())
            }
        }
    }
}