package com.example.rick_and_morty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.R
import com.example.rick_and_morty.model.Character
import com.squareup.picasso.Picasso

class CharactersAdapter(private var Characters: List<Character>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = Characters[position]
        holder.bind(current)
    }

   inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            val title = itemView.findViewById<TextView>(R.id.textView)
            var imageview = itemView.findViewById<ImageView>(R.id.image)
            var status = itemView.findViewById<TextView>(R.id.status)
            title.text = character.name
            status.text = character.situation()
            Picasso.get().load(character.image).into(imageview)
        }

    }

}