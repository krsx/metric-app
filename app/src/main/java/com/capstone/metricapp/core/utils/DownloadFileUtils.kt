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