package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.flow.Flows

@Composable
fun Loading() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            val seconds by Flows.counterFlow.collectAsState(0)
            val minutes = seconds / 60
            val remainingSeconds = seconds % 60
            val counter = String.format("%02d:%02d", minutes, remainingSeconds)
            Text(text = "Loading... App Up Time: $counter",
                Modifier.padding(6.dp))

        }
        LinearProgressIndicator()
    }

}

// TODO fill out preview. NOTE this is above-and-beyond the requirements
//  for the homework assignment.
@Preview
@Composable
fun LoadingPreview() { }
