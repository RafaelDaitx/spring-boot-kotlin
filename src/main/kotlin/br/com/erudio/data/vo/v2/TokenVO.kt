package br.com.erudio.data.vo.v2

import java.util.*

data class TokenVO (

    val username: String? = null,
    val authenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accesToken: String? = null,
    val refreshToken: String? = null,
)