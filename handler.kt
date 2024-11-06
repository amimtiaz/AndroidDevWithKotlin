// Handler Format
Handler(Looper.getMainLooper()).postDelayed({
    // Code to execute after delay
}, 2000) // 2000 milliseconds delay


//Repeating Tasks at Intervals
val handler = Handler(Looper.getMainLooper())
val runnable = object : Runnable {
    override fun run() {
        // Code to execute repeatedly
        handler.postDelayed(this, 1000) // 1 second delay
    }
}
handler.post(runnable)


// Posting to the UI Thread
val handler = Handler(Looper.getMainLooper())
Thread {
    // Background work
    handler.post {
        // Update UI on the main thread
    }
}.start()


// Message Handling in Android Services or Threads
val handler = object : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        // Handle incoming messages
    }
}

// Flash Screen
Handler(Looper.getMainLooper()).postDelayed({
         //startActivity(Intent(this, MainActivity::class.java))
      },1000)
