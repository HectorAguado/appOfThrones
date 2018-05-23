package com.example.hectoraguado.appofthrones

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {

    constructor() : super(){
        this.itemClickListener = null
    }

    constructor(itemClickListener: ((Character, Int) -> Unit)): super(){
        this.itemClickListener = itemClickListener
    }

    private val items = mutableListOf<Character>()
    private val itemClickListener: ((Character, Int) -> Unit)?  // Unit cuando no regresa tipo de dato similara a void


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        // attachs ToRoot, sustituye en lugar de estar contenido, por lo que a false
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
    return items.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = items[position]
        holder.character = item
    }

    fun setCharacters(characters: MutableList<Character>){
        //borramos lo que esté en la instancia de los items
        items.clear()
        //Añadimos todos los items que nos estan mandando
        items.addAll(characters)
        //Notificamos
        notifyDataSetChanged()
    }


    // clase anidada
    inner class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var character: Character? = null
            set(value) {
                value?.let {
                    itemView.label_name.text = value.name
                    itemView.label_title.text = value.title
                    val overlayColor = House.getOverlayColor(value.house.name)
                    itemView.img_overlay.background =ContextCompat.getDrawable(itemView.context, overlayColor)
                }
                field = value
            }
        init {
            itemView.setOnClickListener {
                character?.let {
                    itemClickListener?.invoke(character as Character, position )
                }
            }
        }
    }

}

// clase anidada
//inner class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//    var character: Character? = null
//        set(value) {
//            itemView.findViewById<TextView>(R.id.label_name).text = value?.name
//            field = value
//        }
//    init {
//        itemView.setOnClickListener {
//            character?.let {
//                itemClickListener?.invoke(character as Character, position )
//            }
//        }
//    }
//}