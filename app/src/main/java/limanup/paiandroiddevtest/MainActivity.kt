package limanup.paiandroiddevtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random
import limanup.paiandroiddevtest.databinding.ActivityMainBinding

data class Word (
    val word: String,
    val frequency: Int
        )

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordList = listOf(
            Word("Hello", 3),
            Word("world", 5),
            Word("Kotlin", 2),
            Word("Generative", 1),
            Word("AI", 4),
            Word("ParagraphAI", 6)
        )

        val allWordsList = mutableListOf<String>()
        for (word in wordList) {
            for (i in 0..word.frequency) {
                allWordsList.add(word.word)
            }
        }

        val textView = binding.textSnippitTV
        val generateTextButton = binding.generateTextButton
        generateTextButton.setOnClickListener { getTextSnippt(allWordsList, textView) }

    }

    fun getTextSnippt(allWordsList: MutableList<String>, textView: TextView) {
        allWordsList.shuffle()
        // assuming minimum 5 words make a text snippet
        val outputs = allWordsList.take(Random.nextInt(5, allWordsList.size + 1))
        textView.text = outputs.joinToString(" ")
    }
}