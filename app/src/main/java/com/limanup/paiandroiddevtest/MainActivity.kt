package com.limanup.paiandroiddevtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.limanup.paiandroiddevtest.ui.theme.PaiAndroidDevTestTheme
import kotlin.random.Random

data class Word (
    val word: String,
    val frequency: Int
)

val wordList = listOf(
    Word("Hello", 3),
    Word("world", 5),
    Word("Kotlin", 2),
    Word("Generative", 1),
    Word("AI", 4),
    Word("ParagraphAI", 6)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaiAndroidDevTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Display(wordList)
                }
            }
        }
    }
}


fun generateText(wordList: List<Word>): String {
    val outputs = mutableListOf<String>()
    val totalFreq = wordList.sumOf { it.frequency }
    // assuming minimum 5 words make a text snippet
    val snippetLen = Random.nextInt(5, totalFreq + 1)

    for (i in 1..snippetLen) {
        val randInt = Random.nextInt(totalFreq)
        var cumulateFreq = 0
        for (word in wordList) {
            cumulateFreq += word.frequency
            if (randInt <= cumulateFreq) {
                outputs.add(word.word)
                break
            }
        }
    }

    return outputs.joinToString(" ")
}

@Composable
fun Display(wordList: List<Word>) {
    // https://developer.android.com/jetpack/compose/state
    val displayText = remember {
        mutableStateOf("Click on 'Generate Text' button to start.")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = displayText.value,
                modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp).padding(24.dp)
            )
            Button(onClick = {
                displayText.value = generateText(wordList)
            }) {
                Text(text = "Generate Text")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Display(wordList)
    }
}