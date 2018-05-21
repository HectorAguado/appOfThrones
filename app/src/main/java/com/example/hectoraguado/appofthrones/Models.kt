package com.example.hectoraguado.appofthrones

import java.util.*

data class Character(
        var id: String = UUID.randomUUID().toString(),
        var name: String,
        var born: String,
        var title: String,
        var actor: String,
        var quote: String,
        var father: String,
        var mother: String,
        var spouse: String,
        var house: House
)

data class House(
        var name: String,
        var region: String,
        var words: String
)