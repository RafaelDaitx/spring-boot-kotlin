package br.com.erudio.controller

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.services.PersonService
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
import br.com.erudio.data.vo.v2.PersonVO as PersonVOV2

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
class PersonController {


    @Autowired
    private lateinit var service: PersonService
    // var service: PersonService = PersonService()


    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Find all People", description = "Find all people Description",
            tags = ["People"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (array = ArraySchema(schema = Schema(implementation = PersonVO::class)))
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
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @GetMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Finds a person", description = "Finds a Person Description",
            tags = ["Person"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (schema = Schema(implementation = PersonVO::class))
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
    fun findById(@PathVariable(value="id") id: Long): PersonVO {
        return service.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Adds a Person", description = "Adds a Person Description",
            tags = ["Person"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (schema = Schema(implementation = PersonVO::class))
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

    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person)

    }
  @PostMapping(value = ["/v2"], consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun createV2(@RequestBody person: PersonVOV2): PersonVOV2 {
        return service.createV2(person)

    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Updates a Person", description = "Updates a Person Description",
            tags = ["Person"],
            responses = [
                io.swagger.v3.oas.annotations.responses.ApiResponse(
                        description = "Success", responseCode = "200",
                        content = [
                            Content (schema = Schema(implementation = PersonVO::class))
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

    fun update(@RequestBody PersonVO: PersonVO): PersonVO {
        return service.update(PersonVO)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Deletes Person", description = "Deletes Person Description",
            tags = ["Person"],
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