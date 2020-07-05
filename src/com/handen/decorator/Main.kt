package com.handen.decorator

import com.handen.decorator.decorators.CompressionDecorator
import com.handen.decorator.decorators.EncryptionDecorator
import com.handen.decorator.decorators.FileDataSource



fun main() {
    val salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000"
    val encoded = CompressionDecorator(
        EncryptionDecorator(
            FileDataSource("out/OutputDemo.txt")
        )
    )
    encoded.writeData(salaryRecords)
    val plain = FileDataSource("out/OutputDemo.txt")

    println("- Input ----------------")
    println(salaryRecords)
    println("- Encoded --------------")
    System.out.println(plain.readData())
    println("- Decoded --------------")
    println(encoded.readData())
}