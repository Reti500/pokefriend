package com.example.core.injections

operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T> {
    return if (elements is Collection) {
        val result = ArrayList<T>(this.size + elements.size)
        result.addAll(this)
        result.addAll(elements)
        result
    } else {
        val result = ArrayList<T>(this)
        result.addAll(elements)
        result
    }
}