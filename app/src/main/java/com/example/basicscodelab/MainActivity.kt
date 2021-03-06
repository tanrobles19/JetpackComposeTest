package com.example.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    
    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    }else{
        Greetings()
    }
    
}

@Composable
private fun Greetings(names: List<String> = List(1000) {"$it"}) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: ()-> Unit) {
    
    Surface() {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basis CodeLab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Continue")
            }
        }
    }
    
}


@Composable
fun Greeting(name: String) {

    val expanded = rememberSaveable{ mutableStateOf(false)}

    val extraPadding = if(expanded.value) 48.dp else 0.dp

    println("hello")

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text( if(expanded.value) "show more" else "show less" )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}