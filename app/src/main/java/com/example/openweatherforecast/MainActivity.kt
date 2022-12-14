package com.example.openweatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.openweatherforecast.ui.theme.OpenweatherforecastTheme
import dagger.hilt.android.AndroidEntryPoint

//https://github.com/android/architecture-samples/tree/dev-hilt
//https://www.google.com/search?q=dagger+hilt+in+jet+compose+android+app+example&oq=dagger+hilt+in+jet+compose+android+app+example&aqs=chrome..69i57.16648j0j7&sourceid=chrome&{google:instantExtendedEnabledParameter}{google:omniboxStartMarginParameter}ie=UTF-8

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenweatherforecastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OpenweatherforecastTheme {
        Greeting("Android")
    }
}