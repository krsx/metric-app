package com.capstone.metricapp.core.utils

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream

object DownloadFileUtils {
    fun readByteStreamPDF(context: Context, input: ResponseBody, fileName: String) {
        try {
            val documentsDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val file = generateUniqueFileName(documentsDirectory, fileName)

            input.byteStream().saveToFile(file)
            openDownloadedFile(context, file, "application/pdf")
        } catch (e: Exception) {
            Log.e("Data to PDF", e.toString())
        }
    }

    fun readByteStreamExcel(context: Context, input: ResponseBody, fileName: String) {
        try {
            val documentsDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val file = generateUniqueFileName(documentsDirectory, fileName)

            input.byteStream().saveToFile(file)
            openDownloadedFile(context, file, "application/vnd.ms-excel")
        } catch (e: Exception) {
            Log.e("Data to Excel", e.toString())
        }
    }

    fun logDocumentDirectory(context: Context) {
        val documentsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val pathToDocuments = documentsDirectory.absolutePath
        Log.d("Documents Directory", pathToDocuments)
    }
}

//fun showFileDownloadNotification(context: Context, file: File, fileType: String) {
//    val notificationId = 1 // You can use any unique ID for the notification.
//
//    // Create a notification channel for Android Oreo and higher.
//    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//        val channelId = "file_download_channel"
//        val channelName = "File Download Channel"
//        val importance = NotificationManager.IMPORTANCE_HIGH
//        val channel = NotificationChannel(channelId, channelName, importance)
//
//        val notificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//    }
//
//    // Create an intent to open the saved file when the notification is clicked.
//    val openFileIntent = Intent(Intent.ACTION_VIEW)
//    openFileIntent.setDataAndType(Uri.fromFile(file), fileType)
//    openFileIntent.flags =
//        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_GRANT_READ_URI_PERMISSION
//
//    val pendingIntent = PendingIntent.getActivity(
//        context,
//        0,
//        openFileIntent,
//        PendingIntent.FLAG_UPDATE_CURRENT
//    )
//
//    // Create the notification.
//    val notificationBuilder = NotificationCompat.Builder(context, "file_download_channel")
//        .setContentTitle("File Download Complete")
//        .setContentText("Tap to open the $fileType file.")
//        .setContentIntent(pendingIntent)
//        .setAutoCancel(true)
//        .setPriority(NotificationCompat.PRIORITY_HIGH)
//
//    val notificationManagerCompat = NotificationManagerCompat.from(context)
//    notificationManagerCompat.notify(notificationId, notificationBuilder.build())
//}

private fun generateUniqueFileName(directory: File, filename: String): File {
    var targetFile = File(directory, filename)
    var counter = 1

    while (targetFile.exists()) {
        val fileNameWithoutExtension = filename.substringBeforeLast(".")
        val fileExtension = filename.substringAfterLast(".", "")

        val newFilename = if (fileExtension.isNotEmpty()) {
            "${fileNameWithoutExtension}_[$counter].$fileExtension"
        } else {
            "${filename}_$counter"
        }

        targetFile = File(directory, newFilename)
        counter++
    }

    return targetFile
}

fun InputStream.saveToFile(file: File) {
    use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
}

fun openDownloadedFile(context: Context, file: File, fileType: String) {
    try {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val openFileIntent = Intent(Intent.ACTION_VIEW)
        openFileIntent.setDataAndType(uri, fileType)
        openFileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        openFileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(openFileIntent)
    } catch (e: Exception) {
        Log.e("Open File Error", e.toString())
    }
}