package com.example.hectoraguado.appofthrones


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_character.*
import kotlinx.android.synthetic.main.header_character.*
import kotlinx.android.synthetic.main.item_character.view.*


class DetailFragment : Fragment() {

    companion object {
        fun newInstance(id: String): DetailFragment {
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
            with(character) {
                labelName.text = character.name
                labelTitle.text = title
                labelActor.text = actor
                labelBorn.text = born
                labelParents.text = "${father} & ${mother}"
                labelQuote.text = quote
                labelSpouse.text = spouse

                val houseName = character.house.name
                val overlayColor = House.getOverlayColor(houseName)
                view_overlay.background = ContextCompat.getDrawable(context, overlayColor)

                Picasso.get()
                        .load(character.img)
                        .placeholder(R.drawable.test)
                        .into(img_character)

                btnHouse.text = houseName

//                val baseColor = House.getBaseColor(houseName)
//                btnHouse.backgroundTintList = ContextCompat.getColorStateList(context, baseColor)
//
//                val idDrawable = House.getIcon(houseName)
//                val drawable = ContextCompat.getDrawable(context, idDrawable)
//                btnHouse.setImageDrawable(drawable)
            }
        }
        btnHouse.setOnClickListener {
            if (character != null) {
                showDialog(character.house)            }
        }
    }
    private fun showDialog(house: House){
        val dialog = HouseDialog.newInstance(house)
        dialog.show(childFragmentManager, "house_dialog")
    }

}
