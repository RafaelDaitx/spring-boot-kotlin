package br.com.erudio.exceptions

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.*

@ResponseStatus(HttpStatus.FORBIDDEN)
class InvalidJwtAuthenticationException(exception: String?) : AuthenticationException(exception)