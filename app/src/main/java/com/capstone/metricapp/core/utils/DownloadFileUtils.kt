package com.capstone.metricapp.core.utils

import android.content.Context
import android.os.Environment
import android.util.Log
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

private fun generateUniqueFileName(directory: File, filename: String): File {
    var targetFile = File(directory, filename)
    var counter = 1

    // Iterate until a unique filename is found
    while (targetFile.exists()) {
        // Separate the file name and extension (if any)
        val fileNameWithoutExtension = filename.substringBeforeLast(".")
        val fileExtension = filename.substringAfterLast(".", "")

        // Generate a new filename with a numeric suffix
        val newFilename = if (fileExtension.isNotEmpty()) {
            "${fileNameWithoutExtension}_[$counter].$fileExtension"
        } else {
            "${filename}_$counter"
        }

        // Create a new File object with the updated filename
        targetFile = File(directory, newFilename)

        // Increment the counter for the next iteration
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