package com.handen.decorator.decorators

import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

class FileDataSource(val name: String): DataSource {

    override fun writeData(data: String) {
        val file = File(name)
        FileOutputStream(file).use {
            it.write(data.toByteArray(), 0, data.length)
        }
    }

    override fun readData(): String {
        val file = File(name)
        val buffer = CharArray(file.length().toInt())
        FileReader(name).use {
            it.read(buffer)
        }
        return String(buffer)
    }
}