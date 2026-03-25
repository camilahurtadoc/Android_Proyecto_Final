package com.app.pokeapi.api

import com.app.pokeapi.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Interface utilizada por Retrofit para declarar las peticiones HTTP
interface PokemonApiService {
    /**
     * Petición GET a la API
     *  {name} es el nombre del Pokémon que se envia por parámetro
     */
    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>
}