package org.llschall.dmixtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        enableEdgeToEdge()
        setContent {
            DmixTripTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomePage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to DmixTrip !",
        modifier = modifier,
        color = Color.Blue,
        fontSize = 70.sp,
        FontStyle.Italic,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DmixTripTheme {
        HomePage()
    }
}