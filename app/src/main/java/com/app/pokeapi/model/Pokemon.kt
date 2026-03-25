package com.app.pokeapi.model
// Clase que representa la respuesta principal del Pokémon
data class Pokemon(
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)
//Clase que representa el objeto "sprites" del JSON
data class Sprites(
    // URL de la imagen frontal del Pokémon
    val front_default: String
)
