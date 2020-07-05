package com.handen.decorator.decorators

interface DataSource {

    fun writeData(data: String)

    fun readData(): String
}