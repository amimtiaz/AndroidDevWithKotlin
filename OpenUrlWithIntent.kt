// Intent ===>>
binding.urlBtn.setOnClickListener{
            val url = "https://www.codervive.com"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }

            // verify that there is an app available to handle the intent
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No application available to open the URL", Toast.LENGTH_SHORT).show()
            }
        }
// another method ===>
  binding.urlBtn.setOnClickListener {
            val url = "https://codervive.com/"
            val intent =  Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
