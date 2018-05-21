package com.example.hectoraguado.appofthrones

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

class CharactersFragment : Fragment() {

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        list
    }
    val adapter: CharactersAdapter by lazy {
        val adapter = CharactersAdapter{ item, position ->
            clickListener.onItemClicked(item)
        }
        adapter
    }

    lateinit var clickListener: OnItemClickListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnItemClickListener)
            clickListener = context
        else
            throw IllegalArgumentException("Attached activity doesn't implement CharactersFragment.onItemClickListener")
    }



    // MARK: Life Cycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        return view
    }
    // Se ejecuta cuando la vista ya esta inflada y accesible el UI
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var characters: MutableList<Character> = CharactersRepo.characters
        adapter.setCharacters(characters)

        list.adapter = adapter
    }

    fun showDetails(characterId: String){
        val intent: Intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)

        startActivity(intent)
    }

    interface OnItemClickListener {
        fun onItemClicked(character: Character)
    }
}