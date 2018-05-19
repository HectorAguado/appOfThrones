package com.example.hectoraguado.appofthrones

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class CharacterActivity: AppCompatActivity(){

    val list: RecyclerView by lazy {
        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list
    }
    val adapter: CharactersAdapter by lazy {
        val adapter = CharactersAdapter{ item, position ->
            showDetails(item.id)
        }
        adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        var characters: MutableList<Character> = CharactersRepo.characters
        adapter.setCharacters(characters)

        list.adapter = adapter
    }

    fun showDetails(characterId: String){
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)

        startActivity(intent)
    }
}