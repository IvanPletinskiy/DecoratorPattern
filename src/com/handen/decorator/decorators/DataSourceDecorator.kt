package com.handen.decorator.decorators

open class DataSourceDecorator(private val wrapee: DataSource): DataSource {
    override fun writeData(data: String) {
        wrapee.writeData(data)
    }

    override fun readData() = wrapee.readData()
}