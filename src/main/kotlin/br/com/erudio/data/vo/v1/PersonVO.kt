package br.com.erudio.data.vo.v2

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.Date

@JsonPropertyOrder("id", "address", "firstname", "lastname", "gender")
data class PersonVO (

    var id: Long = 0,

  //  @field:JsonProperty("first_name") -> alterar nome do campo
    var firstName: String = "",

  //  @field:JsonProperty("last_name") -> alterar nome do campo
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    var birthday: Date? = null
)