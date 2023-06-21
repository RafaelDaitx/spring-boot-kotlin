package br.com.erudio.mockito.services

import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.repository.PersonRepository
import br.com.erudio.services.PersonService
import br.com.erudio.unittests.mapper.mocks.MockPerson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional


@ExtendWith(MockitoExtension::class)
class PersonServiceTest {

    private lateinit var inputObject: MockPerson

    @InjectMocks
    private lateinit var service: PersonService

    @Mock
    private lateinit var repository: PersonRepository

    @BeforeEach
    fun setUp() {
        inputObject= MockPerson()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val list = inputObject.mockEntityList() //mockando uma lista de entidade
        `when`(repository.findAll()).thenReturn(list)

        val people = service.findAll()

        assertNotNull(people) // não podem estar nulos
        assertEquals(14, people.size)
        val personOne = people[1]

        assertNotNull(personOne) // não podem estar nulos
        assertNotNull(personOne.key)
        assertNotNull(personOne.links)

        assertTrue(personOne.links.toString().contains("/person/v1/1"))
        assertEquals("Address Test1", personOne.address)
        assertEquals("First Name Test1", personOne.firstName)
        assertEquals("Last Name Test1", personOne.lastName)
        assertEquals("Female", personOne.gender)


        val personFour = people[4]

        assertNotNull(personFour) // não podem estar nulos
        assertNotNull(personFour.key)
        assertNotNull(personFour.links)

        assertTrue(personFour.links.toString().contains("/person/v1/4"))
        assertEquals("Address Test4", personFour.address)
        assertEquals("First Name Test4", personFour.firstName)
        assertEquals("Last Name Test4", personFour.lastName)
        assertEquals("Male", personFour.gender)


        val personSeven = people[7]

        assertNotNull(personSeven) // não podem estar nulos
        assertNotNull(personSeven.key)
        assertNotNull(personSeven.links)

        assertTrue(personSeven.links.toString().contains("/person/v1/7"))
        assertEquals("Address Test7", personSeven.address)
        assertEquals("First Name Test7", personSeven.firstName)
        assertEquals("Last Name Test7", personSeven.lastName)
        assertEquals("Female", personSeven.gender)
    }

    @Test
    fun findById() {
        val person = inputObject.mockEntity(1) //mockando uma entidade
        person.id = 1
        `when`(repository.findById(1)).thenReturn(Optional.of(person))

        val result = service.findById(1)

        assertNotNull(result) // não podem estar nulos
        assertNotNull(result.key)
        assertNotNull(result.links)

        assertTrue(result.links.toString().contains("/person/v1/1"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)

    }

    @Test
    fun create() {
        val entity = inputObject.mockEntity(1) //mockando uma entidade

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = inputObject.mockVO(1) //mockando uma entidade
        val result = service.create(vo)

        assertNotNull(result) // não podem estar nulos
        assertNotNull(result.key)
        assertNotNull(result.links)

        assertTrue(result.links.toString().contains("/person/v1/1"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun createWithNullPerson() {
        val exception: Exception = assertThrows(
            RequiredObjectIsNullException::class.java
        ) {service.create(null)}

        val expectedMessage = "It's not allowed to persist a null object!"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun update() {
        val entity = inputObject.mockEntity(1) //mockando uma entidade

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = inputObject.mockVO(1) //mockando uma entidade
        val result = service.update(vo)

        assertNotNull(result) // não podem estar nulos
        assertNotNull(result.key)
        assertNotNull(result.links)

        assertTrue(result.links.toString().contains("/person/v1/1"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun updateWithNullPerson() {
        val exception: Exception = assertThrows(
                RequiredObjectIsNullException::class.java
        ) {service.update(null)}

        val expectedMessage = "It's not allowed to persist a null object!"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }


    @Test
    fun delete() {
        val entity = inputObject.mockEntity(1) //mockando uma entidade
        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        service.delete(1)
    }
}