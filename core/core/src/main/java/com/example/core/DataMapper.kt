package com.example.core

// DataMapper used for transform I object to O object
interface DataMapper<I, O> {
    // map receive model type I and return output O
    fun map(input: I): O
}