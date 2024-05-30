package org.llschall.dmixtrip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
internal fun Channel(channel: Int) {

    var count by remember {
        mutableIntStateOf(10)
    }

    Column {
        Text(text = "Channel $channel")
        Row {
            Surface(onClick = { count-- }) {
                Text(
                    text = " - ",
                    fontSize = 30.sp,
                    modifier = Modifier.background(Color.Gray)
                )
            }
            Text(text = "$count", fontSize = 30.sp)
            Surface(onClick = { count++ }) {
                Text(
                    text = " + ",
                    fontSize = 30.sp,
                    modifier = Modifier.background(Color.Gray)
                )
            }
        }
    }

}