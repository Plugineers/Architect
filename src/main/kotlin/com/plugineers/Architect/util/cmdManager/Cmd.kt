package com.plugineers.Architect.util.cmdManager


import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
annotation class Cmd(val name: String, val label: String, val usage: String, val aliases: Array<String>, val enabled: Boolean, val permission: String)
