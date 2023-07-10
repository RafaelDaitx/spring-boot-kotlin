package br.com.erudio.controller

import br.com.erudio.data.vo.v2.AccountCredentialVO
import br.com.erudio.services.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    lateinit var authServive: AuthService

    @Operation(summary = "Authenticates a user and return a token")
    @PostMapping(value = ["/signin"])
    fun signIn(@RequestBody data: AccountCredentialVO?): ResponseEntity<*> {
        //Se o client passar um username ou senha nula, retorna um forbidden, se não auttentica o usuario
        return if (data!!.username.isNullOrBlank() || data.password.isNullOrBlank())
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        else authServive.sigiIn(data!!)

    }

    @Operation(summary = "Refresh token for authentication user and returns a token")
    @PutMapping(value = ["/refresh/{username}"])
    fun refreshToken(
        @PathVariable("username") username: String?,
        @RequestHeader("Authorization") refreshToken: String?
    ): ResponseEntity<*> {
        //Se o client passar um username ou senha nula, retorna um forbidden, se não auttentica o usuario
        return if (refreshToken.isNullOrBlank() || username.isNullOrBlank())
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        else authServive.refreshToken(username, refreshToken)

    }
}