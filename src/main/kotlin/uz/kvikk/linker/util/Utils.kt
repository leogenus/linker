package uz.kvikk.linker.util

import org.hashids.Hashids

const val HASHIDS_SALT = "-CSt3DT9d#Qc^Eq!9zY_YDW%hgfq+cEX"
val hashids = Hashids(HASHIDS_SALT, 5)

fun Long?.toHashId(): String? {
    if (this == null) return null
    return hashids.encode(this)
}

fun String?.fromHashId(): Long? {
    if (this == null) return null
    return hashids.decode(this)[0]
}
