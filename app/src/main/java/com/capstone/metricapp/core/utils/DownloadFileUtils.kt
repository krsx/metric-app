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

            Log.e("FILES", file.path.toString())
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

            Log.e("FILES", file.path.toString())
            input.byteStream().saveToFile(file)
            openDownloadedFile(context, file, "application/vnd.ms-excel")
        } catch (e: Exception) {
            Log.e("Data to Excel", e.toString())
        }
    }
}

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
        if (file.exists()) {
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
        } else {
            Log.e("Open PDF Error", "File not found: ${file.absolutePath}")
            // Tangani jika file tidak ditemukan.
        }
    } catch (e: Exception) {
        Log.e("Open File Error", e.toString())
        // Handle other exceptions if necessary.
    }
}