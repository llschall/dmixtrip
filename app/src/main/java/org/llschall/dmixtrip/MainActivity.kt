package org.llschall.dmixtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.llschall.dmixtrip.ui.theme.DmixTripTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("=== Application started ===")
        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                HomePage(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}


@Composable
fun HomePage(modifier: Modifier = Modifier) {

    Column {
        Row {
            Icon(
                imageVector = Icons.Sharp.Home,
                contentDescription = "",
            )
            Text(text = "Welcome to")
        }
        Text(
            text = "DmixTrip",
            modifier = modifier,
            color = Color.Blue,
            fontSize = 50.sp,
            FontStyle.Italic,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Channel(1)
            Channel(2)
            Channel(3)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DmixTripTheme {
        HomePage()
    }
}
