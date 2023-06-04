package br.com.erudio.mapper.custom

import br.com.erudio.data.vo.v2.PersonVO
import br.com.erudio.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {

    fun mapEntityToVo(person: Person) : PersonVO{
        val vo = PersonVO()
        vo.id = person.id
        vo.firstName = person.firstName
        vo.birthday = Date()
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender

        return vo
    }

    fun mapVOToEntity(person: PersonVO) : Person{
        val entity = Person()
        entity.id = person.id
        entity.firstName = person.firstName
        // entity.birthday = person.birthday
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return entity
    }
}