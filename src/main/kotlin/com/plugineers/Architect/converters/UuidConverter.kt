package com.plugineers.Architect.converters

import org.jooq.Converter
import java.nio.ByteBuffer
import java.util.*
import java.util.UUID
import sun.security.krb5.Confounder.bytes



class UuidConverter : Converter<ByteArray, UUID> {
    override fun from(bytes: ByteArray?): UUID {
        val byteBuffer = ByteBuffer.wrap(bytes)
        val high = byteBuffer.long
        val low = byteBuffer.long

        return UUID(high, low)
    }

    override fun to(uuid: UUID?): ByteArray {
        val bb = ByteBuffer.wrap(ByteArray(16))

        bb.putLong(uuid!!.mostSignificantBits)
        bb.putLong(uuid!!.leastSignificantBits)
        return bb.array()
    }

    override fun fromType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun toType(): Class<UUID> {
        return UUID::class.java
    }
}
