package br.com.douglasqueiroz.mavelapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter (private val list: List<Character>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun getItemCount() : Int = list.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int) : ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_charater_list, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val characterImageView = view.findViewById<ImageView>(R.id.image_view_item_character_image)
        private val characterNameTextView = view.findViewById<TextView>(R.id.text_view_item_character_name)


        fun bind(character: Character) {

            characterNameTextView.text = character.name

            Picasso.get()
                .load("${character.thumbnail.path}.${character.thumbnail.extension}")
                .into(characterImageView)
        }
    }

}