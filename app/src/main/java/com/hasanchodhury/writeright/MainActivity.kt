package com.hasanchodhury.writeright

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.hasanchodhury.writeright.R
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rewriteButton: Button = findViewById(R.id.rewrite)
        rewriteButton.setOnClickListener {
            makeApiRequest("http://localhost:5000/rewrite")
        }

        val grammarButton: Button = findViewById(R.id.grammar)
        grammarButton.setOnClickListener {
            makeApiRequest("http://localhost:5000/grammar")
        }

        // Add similar click listeners for other buttons
    }

    private fun makeApiRequest(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle request failure
                Log.e("API_CALL", "Request failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle request success
                val responseBody = response.body?.string()
                Log.d("API_CALL", "Response: $responseBody")
                // Process the response data as needed
            }
        })
    }
}
