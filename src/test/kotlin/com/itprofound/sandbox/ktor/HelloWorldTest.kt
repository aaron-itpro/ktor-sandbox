package com.itprofound.sandbox.ktor

import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationCall
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test

class HelloWorldTest {

    private fun withRequest(
        method: HttpMethod,
        uri: String,
        block: TestApplicationCall.() -> Unit
    ) {
        withTestApplication(Application::main) {
            handleRequest(method, uri).block()
        }
    }

    @Test
    fun helloWorldTest() {
        withRequest(HttpMethod.Get, "/") {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello, world!", response.content)
        }
    }

    @Test
    fun `404Test`() {
        withRequest(HttpMethod.Get, "/nonexistent-endpoint") {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }
}
