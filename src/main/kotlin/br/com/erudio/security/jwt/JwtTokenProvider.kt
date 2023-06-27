package br.com.erudio.security.jwt

import br.com.erudio.data.vo.v2.TokenVO
import com.auth0.jwt.algorithms.Algorithm
import com.ctc.wstx.shaded.msv_core.verifier.regexp.Token
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtTokenProvider {

    @Value("\${security.jwt.token.secret-key:secret}")
    private var secretKey = "secret"

    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validityInMiliseconds: Long = 3_600_000 // Equivalente a 1 hora

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected fun init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccessToken(username: String, roles: List<String>?) : Token {
        val now = Date()
        val validity = Date(now.time + validityInMiliseconds)
        val accessToken = getAccessToken(username, roles, now, validity)
        val refreshToken = getRefreshToken(username, roles, now)

        return TokenVO(
            username = username,
            authenticated = true,
            accesToken = accessToken,
            refreshToken = refreshToken,
            created = now,
            expiration = validity
        )
    }

    private fun getRefreshToken(username: String, roles: List<String>?, now: Date): String? {

    }

    private fun getAccessToken(username: String, roles: List<String>?, now: Date, validity: Date): Any {

    }
}