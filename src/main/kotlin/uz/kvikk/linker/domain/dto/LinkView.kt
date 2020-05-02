package uz.kvikk.linker.domain.dto

import uz.kvikk.linker.domain.enums.LinkStatus
import java.util.*
import javax.persistence.Column

data class LinkView(
        @Column(name = "ID")
        var id: String? = null,
        @Column(name = "CREATED_AT")
        var createdAt: Date? = null,
        @Column(name = "YEAR_NUMBER")
        var yearNumber: Long? = null,
        @Column(name = "YEARLY_SERIES")
        var yearlySeries: Long? = null,
        @Column(name = "LINK_STATUS")
        var linkStatus: LinkStatus? = null,
        @Column(name = "URL")
        var url: String? = null,
        @Column(name = "HASH_ID")
        var hashId: String? = null
)
