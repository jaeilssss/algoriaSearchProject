package com.example.algoriasearchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.algolia.search.saas.Client
import com.algolia.search.saas.CompletionHandler
import com.algolia.search.saas.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var client = Client("Y5P2EINUZX","b17fcc022749dc2ae86e492504aa70f5")

        var index = client.getIndex("Aparts")

        var editText = findViewById<EditText>(R.id.searchEdit)

        var handler = CompletionHandler{content , error ->
            println(content)
        }

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?,start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(str: CharSequence?, start: Int, before: Int, count: Int) {
                    index.searchAsync(Query(str),handler)

            }

        })
    }
}