package br.com.erudio.mockito.services

import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.repository.BookRepository
import br.com.erudio.services.BookService
import br.com.erudio.unittests.mapper.mocks.MockBook
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
class BookServiceTest {

    private lateinit var inputObject: MockBook

    @InjectMocks
    private lateinit var service: BookService

    @Mock
    private lateinit var repository: BookRepository

    @BeforeEach
    fun setUp() {
        inputObject= MockBook()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val list = inputObject.mockEntityList() //mockando uma lista de entidade
        `when`(repository.findAll()).thenReturn(list)

        val people = service.findAll()

        assertNotNull(people) // não podem estar nulos
        assertEquals(14, people.size)
        val bookOne = people[1]

        assertNotNull(bookOne) // não podem estar nulos
        assertNotNull(bookOne.key)
        assertNotNull(bookOne.links)

        assertTrue(bookOne.links.toString().contains("/book/v1/1"))
        assertEquals("Author Test1", bookOne.author)
        assertEquals("Title Test1", bookOne.title)
        assertEquals("Last Name Test1", bookOne.)
        assertEquals("Female", bookOne.gender)


        val bookFour = people[4]

        assertNotNull(bookFour) // não podem estar nulos
        assertNotNull(bookFour.key)
        assertNotNull(bookFour.links)

        assertTrue(bookFour.links.toString().contains("/book/v1/4"))
        assertEquals("Address Test4", bookFour.address)
        assertEquals("First Name Test4", bookFour.firstName)
        assertEquals("Last Name Test4", bookFour.lastName)
        assertEquals("Male", bookFour.gender)


        val bookSeven = people[7]

        assertNotNull(bookSeven) // não podem estar nulos
        assertNotNull(bookSeven.key)
        assertNotNull(bookSeven.links)

        assertTrue(bookSeven.links.toString().contains("/book/v1/7"))
        assertEquals("Address Test7", bookSeven.address)
        assertEquals("First Name Test7", bookSeven.firstName)
        assertEquals("Last Name Test7", bookSeven.lastName)
        assertEquals("Female", bookSeven.gender)
    }

    @Test
    fun findById() {
        val book = inputObject.mockEntity(1) //mockando uma entidade
        book.id = 1
        `when`(repository.findById(1)).thenReturn(Optional.of(book))

        val result = service.findById(1)

        assertNotNull(result) // não podem estar nulos
        assertNotNull(result.key)
        assertNotNull(result.links)

        assertTrue(result.links.toString().contains("/book/v1/1"))
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

        assertTrue(result.links.toString().contains("/book/v1/1"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun createWithNullBook() {
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

        assertTrue(result.links.toString().contains("/book/v1/1"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun updateWithNullBook() {
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