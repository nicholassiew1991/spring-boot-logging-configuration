package io.github.nicholassiew.springboot.loggingconfiguration.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController {

  private val logger: Logger = LoggerFactory.getLogger(HomeController::class.java)

  @GetMapping("/")
  fun index(): ResponseEntity<Any> {
    this.logger.trace("This is Trace")
    this.logger.debug("This is Debug")
    this.logger.info("This is Info")
    this.logger.warn("This is Warn")
    this.logger.error("This is Error")
    return ResponseEntity.ok(mapOf("message" to "Hello World"))
  }

  @GetMapping("/ex")
  fun exception(): ResponseEntity<Any> {
    throw Exception("This is Exception")
  }
}