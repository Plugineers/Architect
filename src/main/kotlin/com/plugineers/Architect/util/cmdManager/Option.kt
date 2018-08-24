package com.plugineers.Architect.util.cmdManager

import java.lang.annotation.Repeatable

@Repeatable(CliOptions::class)
annotation class Option(val opt: String = "", val longOpt: String = "", val hasArg: Boolean = false, val description: String = "", val required: Boolean = false, val argCount: Int = 0)
