package com.example.hectoraguado.appofthrones


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    companion object {
        fun newInstance(id: String): DetailFragment{
            val instance = DetailFragment()

            val args = Bundle()
            args.putString("key_id", id)
            instance.arguments = args

            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments.getString("key_id")
        val character = CharactersRepo.findCharacterById(id)
        character?.let {
            with(character){
                labelName.text = character.name
                labelTitle.text = title
                labelActor.text = actor
                labelBorn.text = born
                labelParents.text = "${father} & ${mother}"
                labelQuote.text = quote
                labelSpouse.text = spouse
                button.text = house.words
            }
        }
        button.setOnClickListener { Toast.makeText(context, character?.house?.name, Toast.LENGTH_SHORT).show() }
    }

}
