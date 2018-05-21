package com.example.hectoraguado.appofthrones

object CharactersRepo {
    var characters: MutableList<Character> = mutableListOf() // lista mutable vacía
    get() {
        if (field.isEmpty())
            field.addAll(dummyCharacters())
        return  field
    }

    private fun dummyCharacters(): MutableList<Character> {
        val dummies: MutableList<Character> = (1..10).map {
            intToCharacter(it)
        }.toMutableList()
        return dummies
    }

    fun findCharacterById(id: String): Character?{
        return characters.find {character ->
            character.id == id
        }
    }

    private fun intToCharacter(int: Int): Character {
        return Character(
                    name = "Personaje ${int}",
                    born = "Nació en ${int}",
                    title = "Título ${int}",
                    actor = "Actor ${int}",
                    father = "Padre ${int}",
                    mother = "Madre ${int}",
                    quote = "Quote ${int}",
                    spouse = "Esposa  ${int}",
                    house = House(name = "Stark", region = "Invernalia", words = "Se acerca el invierno")
        )
    }
}