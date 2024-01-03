package com.example.coroutines2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 1. Define the scope in which we want our coroutine to start
        GlobalScope.launch(Dispatchers.IO) {
        // 2. define what our coroutine should do
            // this will only pause the current cortn and not block the main thread
            delay(3000)
            Log.d(TAG, "Coroutine says hello from thread ${Thread.currentThread().name}")
            val networkCallAnswer = doNetworkCall()
            val networkCallAnswer2 = doNetworkCall2()
            Log.d(TAG, networkCallAnswer)
            Log.d(TAG, networkCallAnswer2)
            // now that we got the answer from the nework call we want display that in the UI
            withContext(Dispatchers.Main){
//                tv.dummy.text = answer
                Log.d(TAG, "Setting text in  thread ${Thread.currentThread().name}")
            }
        }

        // we can start a crtn in the main thread
        runBlocking {

        }
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")
    }

    // creatine our own suspend function that simulates a network call
    suspend fun doNetworkCall(): String {
        delay(3000)
        return "This is the answer"
    }

    suspend fun doNetworkCall2(): String {
        delay(3000)
        return "This is the answer 2"
    }
}