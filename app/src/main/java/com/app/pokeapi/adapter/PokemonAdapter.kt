package com.app.pokeapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pokeapi.R
import com.app.pokeapi.model.Pokemon
import com.squareup.picasso.Picasso

// Adapter encargado de conectar los datos con el RecyclerView
class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private var pokemon: Pokemon? = null

    //Método que permite actualizar el Pokémon mostrado
    //Cuando se reciben datos desde la API se llama a este método
    fun setPokemon(p: Pokemon) {
        pokemon = p
        notifyDataSetChanged()
    }
    // ViewHolder: representa cada item visual del RecyclerView
    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txtName)
        val height: TextView = view.findViewById(R.id.txtHeight)
        val weight: TextView = view.findViewById(R.id.txtWeight)
        val image: ImageView = view.findViewById(R.id.imgPokemon)
    }
    // Se ejecuta cuando el RecyclerView necesita crear un nuevo item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }
    // Se ejecuta para asociar los datos al item visual
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        // Si el Pokémon existe se cargan sus datos en la vista
        pokemon?.let {
            holder.name.text = "Nombre: ${it.name}"
            holder.height.text = "Altura: ${it.height}"
            holder.weight.text = "Peso: ${it.weight}"
           // Descarga y carga la imagen en el ImageView usando Picasso
            Picasso.get()
                .load(it.sprites.front_default)
                .into(holder.image)
        }
    }
    // Cantidad de elementos que tendrá el RecyclerView (Solo 1 pokemon pide el ejercicio)
    override fun getItemCount(): Int {
        return if (pokemon == null) 0 else 1
    }
}