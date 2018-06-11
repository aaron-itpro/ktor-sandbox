package com.itprofound.sandbox.ktor

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::main).start(wait = true)
}

fun Application.main() {
    routing {
        helloWorld()
        fail()
    }
}

fun Routing.helloWorld() = get("/") {
    call.respondText("Hello, world!", ContentType.Text.Html)
}

fun Routing.fail() = route("{...}") {
    handle {
        call.respond(HttpStatusCode.NotFound, "I'm sorry, are you lost?")
    }
}

