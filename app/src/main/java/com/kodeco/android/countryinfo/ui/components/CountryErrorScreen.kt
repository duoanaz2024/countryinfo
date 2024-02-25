package com.kodeco.android.countryinfo.ui.components

import android.app.Activity
import android.content.Intent.getIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.R

@Composable
fun CountryErrorScreen(headline: String, subtitle: String) {
    val activity = (LocalContext.current as? Activity)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.graphicsLayer(scaleY = 2f, scaleX = 2f),
                imageVector = Icons.Default.Warning,
                contentDescription = "Warning",
                tint = Color(android.graphics.Color.parseColor("#FF5252"))
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = headline
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = subtitle
            )
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .align(alignment = Alignment.BottomCenter),
            onClick = {
                activity?.finish()
                activity?.startActivity(activity.intent);
            }) {
            Text("Refresh")
        }
    }
}

// TODO fill out this preview.
@Preview
@Composable
fun CountryErrorScreenPreview() { }
