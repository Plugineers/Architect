package com.plugineers.Architect.entities.enums

enum class PlayerAccess {
    LITE,
    RESIDENT,
    ADMIN,

    NONE;

    companion object {
        fun valueOf(search: String): PlayerAccess? {
            values().forEach { s ->
                if (s.name == search.toUpperCase()) {
                    return s
                }
            }
            return null
        }

    }
}
