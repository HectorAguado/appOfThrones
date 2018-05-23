package com.example.hectoraguado.appofthrones

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_characters.*

class CharacterActivity: AppCompatActivity(), CharactersFragment.OnItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        // Iniciar transacción
        if (savedInstanceState == null) { // primera vez q se crea
            val fragment = CharactersFragment()

            this.supportFragmentManager
                    .beginTransaction()
                    .add(R.id.list_container, fragment)
                    .commit()
        }
    }

    override fun onItemClicked(character: Character) {
        if (isDetailViewAvailable())
            showFragmentDetails(character.id)
        else
            launchDetailActivity(character.id)
    }

    private fun isDetailViewAvailable() = detail_container != null

    private fun showFragmentDetails(characterId: String) {
        //Creamos el fragmento
        val detailFragment = DetailFragment.newInstance(characterId)


        //Mostramos el fragment por pantalla
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_container, detailFragment)
                .commit()
    }

    private fun launchDetailActivity(characterId: String){
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)

        startActivity(intent)
    }

}