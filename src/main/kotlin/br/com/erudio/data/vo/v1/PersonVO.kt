package br.com.erudio.data.vo.v2

import br.com.erudio.data.vo.v1.PersonVO
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.hibernate.metamodel.RepresentationMode
import org.springframework.hateoas.RepresentationModel
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
)   : RepresentationModel<PersonVO>()