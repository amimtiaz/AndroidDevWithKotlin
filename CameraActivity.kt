// Activity XML
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <Button
        android:id="@+id/openCameraBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Camera"/>

    <Button
        android:id="@+id/savePhotoBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Save Photo"
        android:visibility="gone"/>  <!-- Initially hidden -->

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"/>

</LinearLayout>

*/
/*-------------XML Code End Here--------------*/


// Activity Code Start
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.Manifest
import android.content.ContentValues
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import com.example.practice_kotlin.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private var imageUri: Uri? = null

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                openCamera()
            } else {
                showPermissionDeniedMessage()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the button click listeners
        binding.openCameraBtn.setOnClickListener {
            requestCameraPermission()
        }

        binding.savePhotoBtn.setOnClickListener {
            requestStoragePermission() // Request storage permission before saving the photo
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                openCamera()
            }
            else -> {
                cameraPermissionRequest.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            // Create a file to save the image
            val photoFile = createImageFile()
            imageUri = FileProvider.getUriForFile(this, "${packageName}.provider", photoFile) // Use FileProvider to get the URI
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri) // Pass the URI to the camera intent
            cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION) // Grant write permission for the URI
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Display the image using the URI
            binding.imageView.setImageURI(imageUri)
            binding.savePhotoBtn.visibility = Button.VISIBLE // Show the Save button after taking a photo
        }
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = getExternalFilesDir(null)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
    }

    private fun savePhoto() {
        val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap

        // Create a content resolver and set the image name and description
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "CapturedImage_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // Save to Pictures directory
        }

        // Insert the image into the MediaStore
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        // Use a try-catch block to handle potential exceptions
        try {
            if (uri != null) {
                // Open an output stream to write the bitmap
                val outputStream = contentResolver.openOutputStream(uri)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                } // Save as JPEG with 100% quality
                outputStream?.close() // Close the output stream
                Toast.makeText(this, "Photo saved to gallery", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save photo", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error saving photo: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
        } else {
            savePhoto() // If permission is already granted, save the photo
        }
    }

    private fun showPermissionDeniedMessage() {
        Toast.makeText(this, "Camera permission is required to take photos.", Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                savePhoto() // Call savePhoto if permission is granted
            } else {
                Toast.makeText(this, "Storage permission is required to save photos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val STORAGE_PERMISSION_CODE = 2
    }
}
/*-------------Activity Code End Here--------------*/
