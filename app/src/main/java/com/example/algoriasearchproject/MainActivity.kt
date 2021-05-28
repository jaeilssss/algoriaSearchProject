package com.example.algoriasearchproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.algolia.search.saas.Client
import com.algolia.search.saas.CompletionHandler
import com.algolia.search.saas.Query
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var rcv : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var client = Client("","")

        var index = client.getIndex("Aparts")

        var editText = findViewById<EditText>(R.id.searchEdit)
        var adapter = adapter(ArrayList<Apt>())
         rcv = findViewById(R.id.rcv)
        var manager= LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        rcv.layoutManager =manager
        rcv.adapter = adapter
        rcv.setHasFixedSize(true)
        var handler = CompletionHandler{content , error ->
            var list = ArrayList<Apt>()

           var jsonArray  = content?.getJSONArray("hits")
            if (jsonArray != null) {
                for(i in 1 until jsonArray.length()) {
                    var json = jsonArray?.getJSONObject(i)
                    var apt = Apt(json.getString("aptName"),json.getString("doroJuso"))
                    list.add(apt)
                }
                adapter.list = list
                adapter.resetting()
            }

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