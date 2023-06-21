package br.com.erudio.controller

import br.com.erudio.services.BookService
import br.com.erudio.util.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

import io.swagger.v3.oas.models.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import br.com.erudio.data.vo.v2.BookVO as BookVOV2

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books")
class BookController {


    @Autowired
    private lateinit var service: BookService
    // var service: BookService = BookService()


    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Find all Books", description = "Find all people Description",
            tags = ["Books"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (array = ArraySchema(schema = Schema(implementation = BookVOV2::class)))
                        ]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "No content", responseCode = "204",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Bad Request", responseCode = "400",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Unauthorized", responseCode = "401",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Not found", responseCode = "404",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Internal server error", responseCode = "500",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
            ])
    fun findAll(): List<BookVOV2> {
        return service.findAll()
    }

    @GetMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Finds a book", description = "Finds a Book Description",
            tags = ["Book"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (schema = Schema(implementation = BookVOV2::class))
                        ]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "No content", responseCode = "204",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Bad Request", responseCode = "400",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Unauthorized", responseCode = "401",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Not found", responseCode = "404",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Internal server error", responseCode = "500",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
            ])
    fun findById(@PathVariable(value="id") id: Long): BookVOV2 {
        return service.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Adds a Book", description = "Adds a Book Description",
            tags = ["Book"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (schema = Schema(implementation = BookVOV2::class))
                        ]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Bad Request", responseCode = "400",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Unauthorized", responseCode = "401",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Internal server error", responseCode = "500",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
            ])

    fun create(@RequestBody book: BookVOV2): BookVOV2 {
        return service.create(book)

    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Updates a Book", description = "Updates a Book Description",
            tags = ["Book"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (schema = Schema(implementation = BookVOV2::class))
                        ]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Bad Request", responseCode = "400",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Unauthorized", responseCode = "401",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Not found", responseCode = "404",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Internal server error", responseCode = "500",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
            ])

    fun update(@RequestBody BookVO: BookVOV2): BookVOV2 {
        return service.update(BookVO)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Deletes Book", description = "Deletes Book Description",
            tags = ["Book"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Bad Request", responseCode = "400",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Unauthorized", responseCode = "401",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Not found", responseCode = "404",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Internal server error", responseCode = "500",
                        content = [Content (schema = Schema(implementation = Unit::class))]
                ),
            ])
    fun delete(@PathVariable(value="id") id: Long) : ResponseEntity<*>{
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}