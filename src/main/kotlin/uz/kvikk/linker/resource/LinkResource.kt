package uz.kvikk.linker.resource

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uz.kvikk.linker.service.LinkService
import uz.kvikk.linker.domain.rest.LinkRequest

@RestController
@RequestMapping("/api/link")
class LinkResource(
        private val linkService: LinkService
) {
    @PostMapping("create")
    fun create(@RequestBody body: LinkRequest): ResponseEntity<*> =
            ResponseEntity.ok(linkService.create(body))
}

