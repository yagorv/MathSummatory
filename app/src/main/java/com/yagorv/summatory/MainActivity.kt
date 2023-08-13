package com.yagorv.summatory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yagorv.summatory.ui.theme.SummatoryTheme
import kotlinx.coroutines.delay
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SummatoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box {
                        AutoUpdateNumberCenterScreen(obj1.first, 500)
                    }
                }
            }
        }
    }
}

@Composable
fun AutoUpdateNumberCenterScreen(list: List<Int>, durationMilliseconds: Long) {
    var currentNumber by remember { mutableStateOf(0) }

    LaunchedEffect(currentNumber) {
        while (currentNumber <= list.size - 1) {
            delay(durationMilliseconds)
            currentNumber++
        }
    }
    
    if(currentNumber == list.size){
        // progress finish and show last page
        Column {
            Text(text = "fiinish!")
        }
    }else{
        // progress not finish and show next number
        Column {
            NumberCenterScreen(number = list[currentNumber])
        }
    }

}

@Composable
fun NumberCenterScreen(number: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(generateRandomColor(), generateRandomColor())
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black
            )
        }
    }
}

fun generateRandomColor(): Color {
    val random = Random()
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red, green, blue)
}
