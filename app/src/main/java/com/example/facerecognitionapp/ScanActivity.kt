package com.example.facerecognitionapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.UploadCallback
import com.google.firebase.auth.FirebaseAuth
import java.io.File
import java.io.FileOutputStream




class ScanActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var captureButton: Button
    private lateinit var uploadButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var imageBitmap: Bitmap
    private val CAMERA_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        auth = FirebaseAuth.getInstance()
        imageView = findViewById(R.id.imageView)
        captureButton = findViewById(R.id.captureButton)
        uploadButton = findViewById(R.id.uploadButton)

        captureButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
            }
        }

        uploadButton.setOnClickListener {
            if (::imageBitmap.isInitialized) {
                uploadToCloudinary(imageBitmap)
            } else {
                Toast.makeText(this, "Capture an image first!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }
    override fun onStart() {
        super.onStart()
    }

    private fun uploadToCloudinary(imageBitmap: Bitmap) {
        val file = File(cacheDir, "temp_image.jpg")
        val outputStream = FileOutputStream(file)
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.flush()
        outputStream.close()

        MediaManager.get().upload(file.absolutePath)
            .option("resource_type", "image")
            .callback(object : UploadCallback {
                override fun onStart(requestId: String?) {
                    // Called when upload starts
                    Log.d("Upload", "Upload started")
                }

                override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                    // Called during upload to track progress
                    val progress = (bytes.toFloat() / totalBytes.toFloat()) * 100
                    Log.d("Upload", "Progress: $progress%")
                }

                override fun onSuccess(requestId: String?, resultData: Map<*, *>?) {
                    // Called when the upload is successful
                    Log.d("Upload", "Upload Successful: ${resultData?.get("secure_url")}")
                }

                override fun onError(requestId: String?, error: com.cloudinary.android.callback.ErrorInfo?) {
                    // Called if an error occurs during the upload
                    Log.e("Upload", "Upload Failed: ${error?.description}")
                }

                override fun onReschedule(requestId: String?, error: com.cloudinary.android.callback.ErrorInfo?) {
                    // Called if the upload is rescheduled
                    Log.d("Upload", "Upload Rescheduled")
                }
            })
            .dispatch()

    }
}
