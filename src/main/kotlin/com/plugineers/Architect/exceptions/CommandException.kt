package com.plugineers.Architect.exceptions

import com.plugineers.Architect.entities.enums.Response
import java.util.logging.Level

class CommandException(level: Level, message : String) : Exception(message) {
}