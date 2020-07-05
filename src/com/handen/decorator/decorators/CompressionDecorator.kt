package com.handen.decorator.decorators

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.zip.Deflater
import java.util.zip.DeflaterOutputStream
import java.util.zip.InflaterInputStream


class CompressionDecorator(wrapee: DataSource) : DataSourceDecorator(wrapee) {
    var compLevel = 6

    override fun writeData(data: String) {
        super.writeData(compress(data))
    }

    override fun readData(): String {
        return decompress(super.readData())
    }

    private fun compress(stringData: String): String {
        val data = stringData.toByteArray()

        val bout = ByteArrayOutputStream(512)
        val dos = DeflaterOutputStream(bout, Deflater(compLevel))
        dos.write(data)
        dos.close()
        bout.close()
        return Base64.getEncoder().encodeToString(bout.toByteArray())
    }

    private fun decompress(stringData: String): String {
        val data = Base64.getDecoder().decode(stringData)

        val `in` = ByteArrayInputStream(data)
        val iin = InflaterInputStream(`in`)
        val bout = ByteArrayOutputStream(512)
        var b: Int
        do {
            b = iin.read()
            bout.write(b)
        } while (b != -1)
        `in`.close()
        iin.close()
        bout.close()
        return String(bout.toByteArray())
    }
}