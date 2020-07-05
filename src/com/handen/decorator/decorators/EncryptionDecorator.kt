package com.handen.decorator.decorators

import java.util.*

class EncryptionDecorator(wrapee: DataSource): DataSourceDecorator(wrapee) {
    override fun writeData(data: String) {
        super.writeData(encode(data))
    }

    private fun encode(data: String): String {
        val result = data.toByteArray()
        for(i in 0 until result.size) {
            result[i] = (result[i] + 1).toByte()
        }
        return Base64.getEncoder().encodeToString(result)
    }

    override fun readData() = decode(super.readData())

    private fun decode(data: String): String {
        val result = Base64.getDecoder().decode(data)
        for (i in result.indices) {
            result[i] = (result[i] - 1).toByte()
        }
        return String(result)
    }
}