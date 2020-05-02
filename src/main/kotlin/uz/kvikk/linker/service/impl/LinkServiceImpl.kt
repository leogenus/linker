package uz.kvikk.linker.service.impl

import org.jooq.DSLContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import uz.kvikk.linker.domain.dto.LinkView
import uz.kvikk.linker.service.LinkService
import uz.kvikk.linker.util.toHashId
import uz.kvikk.linker.domain.rest.LinkRequest
import java.util.*

@Service
class LinkServiceImpl(private val dsl: DSLContext) : LinkService {

    override fun oneByHashId(hashId: String): LinkView? =
            dsl.fetchOne("""
                    select r.* 
                    from l_link r
                    where r.hash_id={0}
                """.trimIndent(), hashId.trim())?.into(LinkView::class.java)

    override fun one(tDsl: DSLContext, id: String): LinkView =
            tDsl.fetchOne("""
                    select r.* 
                    from l_link r
                    where r.id={0}
                """.trimIndent(), id).into(LinkView::class.java)

    override fun create(body: LinkRequest): LinkView =
            dsl.transactionResult { configuration ->
                if (body.url.isNullOrEmpty())
                    throw ResponseStatusException(HttpStatus.BAD_REQUEST, "bad-request.url.empty", RuntimeException("This Url should not be empty!"))

                val tDsl = configuration.dsl()
                val link = tDsl.fetchOne("""
                    select r.* 
                    from l_link r
                    where r.url={0}
                """.trimIndent(), body.url)?.into(LinkView::class.java)

                if (link != null) link
                else {
                    val id = UUID.randomUUID().toString()
                    val yearNumber = tDsl.fetchOne("select EXTRACT(YEAR FROM now())")
                            .into(Long::class.java)

                    val yearlySeries = tDsl.fetchOne("""
                    select max(r.yearly_series)
                    from l_link r
                    where r.year_number={0}
                    """.trimIndent(), yearNumber)?.into(Long::class.java) ?: 0

                    val serial = yearlySeries + 1;
                    val hashId = "$yearNumber${serial}".toLong().toHashId()
                    tDsl.execute("""
                        insert into l_link(
                        id,
                        year_number,
                        yearly_series,
                        url,
                        hash_id)
                        values({0}, {1}, {2}, {3}, {4})
                    """.trimIndent(),
                            id,
                            yearNumber,
                            serial,
                            body.url,
                            hashId)

                    one(tDsl, id)
                }
            }
}
