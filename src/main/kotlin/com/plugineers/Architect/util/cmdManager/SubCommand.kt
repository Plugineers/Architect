package com.plugineers.Architect.util.cmdManager

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
annotation class SubCommand(val name: String, val parent: String, val label: String, val usage: String, val aliases: Array<String> = arrayOf(), val enabled: Boolean, val permission: String)
