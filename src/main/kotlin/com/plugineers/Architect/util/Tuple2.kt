package com.plugineers.Architect.util

class Tuple2<K, V>(k: K, v: V) {
    private var key: K

    private var value: V


    init {
        this.key = k
        this.value = v
    }
}
