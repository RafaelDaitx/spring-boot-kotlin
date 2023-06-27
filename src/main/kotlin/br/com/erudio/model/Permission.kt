package br.com.erudio.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "permission")
class Permission : GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "description", length = 255)
    var description: String? = null

    override fun getAuthority() = description!! //por infererencia, logo que descripiton é uma
                                                //String e o oveeride espera uma string

}