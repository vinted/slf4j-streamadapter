package com.vinted.slf4j.adapter

import lt.neworld.kupiter.testFactory
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.io.ByteArrayOutputStream
import kotlin.reflect.KFunction2

class SLF4JOutputStreamAdapterTest {
    val outputStream = ByteArrayOutputStream()

    val fixture = SLF4JOutputStreamAdapter(outputStream)

    val fixtureBasicMethods: List<KFunction2<SLF4JOutputStreamAdapter, String, Unit>> = listOf(
        SLF4JOutputStreamAdapter::warn,
        SLF4JOutputStreamAdapter::error,
        SLF4JOutputStreamAdapter::info,
        SLF4JOutputStreamAdapter::trace,
        SLF4JOutputStreamAdapter::debug
    )

    @Test
    @TestFactory
    fun simple() = testFactory {
        fixtureBasicMethods.forEach { method ->
            test(method.name) {
                method.invoke(fixture, "Foo bar")

                val output = outputStream.toString()

                assertTrue(output.contains("Foo bar")) {
                    "Logging of method ${method.name}(\"Foo bar\") doesn't write message to output stream. Output stream: $output"
                }
            }
        }
    }
}
