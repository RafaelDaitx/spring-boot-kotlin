package br.com.erudio.services

import br.com.erudio.data.vo.v2.AccountCredentialVO
import br.com.erudio.data.vo.v2.TokenVO
import br.com.erudio.repository.UserRepository
import br.com.erudio.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuthService {

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    private val logger = Logger.getLogger(AuthService::class.java.name)

     fun sigiIn(data: AccountCredentialVO) : ResponseEntity<*> {
         logger.info("Trying log user ${data.username}")
         return try {
            val username = data.username
            val password = data.password
             authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
             val user = repository.findByUsername(username)

             val tokenResponse: TokenVO = if(user != null){
                 tokenProvider.createAccessToken(username!!, user.roles)
             } else {
                 throw UsernameNotFoundException("Username $username not found")
             }
             ResponseEntity.ok(tokenResponse)
         }catch (e: AuthenticationException){
             throw BadCredentialsException("Invalid username or password supplioed!")
         }
     }

    fun refreshToken(username:String, refreshToken: String) : ResponseEntity<*> {
         logger.info("Try get refresh token to user $username")

             val user = repository.findByUsername(username)
             val tokenResponse: TokenVO = if(user != null){
                 tokenProvider.refreshToken(username)
             } else {
                 throw UsernameNotFoundException("Username $username not found")
             }
            return ResponseEntity.ok(tokenResponse)
     }
}