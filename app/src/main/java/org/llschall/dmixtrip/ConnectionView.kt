package org.llschall.dmixtrip

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.llschall.dmixtrip.model.ConnectionModel

@Preview
@Composable
fun ConnectionView() {

    val model = remember {
        ConnectionModel()
    }

    Column {
        for (i in 0..<model.logs.size) {
            Text(
                text = model.logs[i],
                fontSize = 18.sp
            )
        }
        Button(onClick = {
            model.onStart()
        }) {
            Text(
                text = "Start",
                fontSize = 28.sp
            )
        }
    }
}