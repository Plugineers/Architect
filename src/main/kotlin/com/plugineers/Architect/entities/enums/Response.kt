package com.plugineers.Architect.entities.enums

enum class Response private constructor(var defaultMsg : String) {
    EXCEEDS_LITE_MAX_SIZE(""),
    SUCCESS(""),
    INVALID_BRUSH(""),
    NO_REGISTERED_BRUSH(""),
    NO_SELECTED_BRUSH(""),
    WARNING_LARGE_BRUSH(""),
    SUCCESS_BRUSH_SET_MAX(""),
    NO_CUSTOM_TERRAFORMER(""),

    NO_ACCESS(""),

    INVALID_TERRAFORMER(""),
    BANNED_BRUSH(""),
    BANNED_MATERIAL(""),
    EXCEEDS_MAX_SIZE("");

    fun defaultMessage() : String {
        return defaultMsg
    }
}
