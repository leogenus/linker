package uz.kvikk.linker.service

import org.jooq.DSLContext
import uz.kvikk.linker.domain.dto.LinkView
import uz.kvikk.linker.domain.rest.LinkRequest

interface LinkService {
    fun oneByHashId(hashId: String): LinkView?

    fun one(tDsl: DSLContext, id: String): LinkView

    fun create(body: LinkRequest): LinkView
}
