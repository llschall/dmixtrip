package org.llschall.dmixtrip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.llschall.dmixtrip.model.ConnectionModel

@Preview
@Composable
fun ConnectionView() {

    val model: ConnectionModel = viewModel()

    Column {
        for (i in 0..<model.logs.size) {
            Text(
                text = model.logs[i],
                fontSize = 18.sp
            )
        }
        Row {
            Button(onClick = {
                model.onStart()
            }) {
                Text(
                    text = "Start",
                    fontSize = 28.sp
                )
            }
            Button(onClick = {
                model.onStatus()
            }) {
                Text(
                    text = "Scan",
                    fontSize = 28.sp
                )
            }
            Button(onClick = {
                model.onConnect()
            }) {
                Text(
                    text = "Connect",
                    fontSize = 28.sp
                )
            }
            Button(onClick = {
                model.onSend()
            }) {
                Text(
                    text = "Send",
                    fontSize = 28.sp
                )
            }
        }
        Text(text = model.status[0])
    }
}