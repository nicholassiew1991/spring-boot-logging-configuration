package io.github.nicholassiew.springboot.loggingconfiguration.filters

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.IdGenerator
import org.springframework.util.JdkIdGenerator

@Component
@Order(1)
class InitialiseRequestFilter : Filter {

  private val idGenerator: IdGenerator = JdkIdGenerator()

  private val logger: Logger = LoggerFactory.getLogger(InitialiseRequestFilter::class.java)

  override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
    try {
      val cid = idGenerator.generateId().toString()
      MDC.put("cid", cid)
      filterChain!!.doFilter(request, response)
    } catch (ex: Exception) {
      this.logger.error(ex.message, ex)
    }
    finally {
      MDC.clear()
    }
  }

}