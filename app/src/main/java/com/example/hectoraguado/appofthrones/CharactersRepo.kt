package com.example.hectoraguado.appofthrones

import android.content.Context
import com.android.volley.Request
import java.util.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

const val URL_CHARACTERS = "http://5b04924d0f8d4c001440b108.mockapi.io/characters"

object CharactersRepo {
    //    var characters: MutableList<Character> = mutableListOf() // lista mutable vacía
//        get() {
//            if (field.isEmpty())
//                field.addAll(dummyCharacters())
//            return field
//        }
    private var characters: MutableList<Character> = mutableListOf()

    fun requestCharacters(context: Context,
                          success: ((MutableList<Character>) -> Unit),
                          error: (() -> Unit)) {
        if (characters.isEmpty()) {
            val request = JsonArrayRequest(Request.Method.GET, URL_CHARACTERS, null,
                    { response ->
                        characters = parseCharacters(response)
                        success.invoke(characters)
                    },
                    { volleyError ->
                        error.invoke()
                    })
            Volley.newRequestQueue(context)
                    .add(request)
        } else {
            success.invoke(characters)
        }
    }

    private fun parseCharacters(jsonArray: JSONArray): MutableList<Character> {
        val characters = mutableListOf<Character>()
        for (index in 0..(jsonArray.length() - 1)) {
            val character = parseCharacter(jsonArray.getJSONObject(index))
            characters.add(character)
        }

        return characters
    }
    private fun parseCharacter(jsonCharacter: JSONObject): Character {
        return Character(
                jsonCharacter.getString("id"),
                jsonCharacter.getString("name"),
                jsonCharacter.getString("born"),
                jsonCharacter.getString("title"),
                jsonCharacter.getString("actor"),
                jsonCharacter.getString("quote"),
                jsonCharacter.getString("father"),
                jsonCharacter.getString("mother"),
                jsonCharacter.getString("spouse"),
                jsonCharacter.getString("img"),
                parseHouse(jsonCharacter.getJSONObject("house")))
    }
    private fun parseHouse(jsonHouse: JSONObject): House {
        return House(
                jsonHouse.getString("name"),
                jsonHouse.getString("region"),
                jsonHouse.getString("img"),
                jsonHouse.getString("words")
        )
    }



    fun findCharacterById(id: String): Character? {
        return characters.find { character ->
            character.id == id
        }
    }
}
//private fun dummyCharacters(): MutableList<Character> {
//    val dummies: MutableList<Character> = (1..10).map {
//        intToCharacter(it)
//    }.toMutableList()
//    return dummies
//}
//private fun intToCharacter(int: Int): Character {
//    return Character(
//            name = "Personaje ${int}",
//            born = "Nació en ${int}",
//            title = "Título ${int}",
//            actor = "Actor ${int}",
//            father = "Padre ${int}",
//            mother = "Madre ${int}",
//            quote = "Quote ${int}",
//            spouse = "Esposa  ${int}",
//            house = dummyHouse()
//    )
//}
//
//private fun dummyHouse(): House {
//    val ids = arrayOf("stark", "lannister", "tyrell", "arryn", "baratheon", "tully")
//    val randomIdPosition = Random().nextInt(ids.size)
//    return House(name = ids[randomIdPosition],
//            region = "Region",
//            words = "Lema")
//}