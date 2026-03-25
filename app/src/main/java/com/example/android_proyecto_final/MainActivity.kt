package com.example.android_proyecto_final

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pokeapi.api.PokemonApiService
import com.app.pokeapi.model.Pokemon
import com.google.android.material.appbar.MaterialToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view)
        pokemonAdapter = PokemonAdapter()

        recyclerView.adapter = pokemonAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Codigo de Retrofit para consumir la API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Implementando la interface de la API
        val apiService = retrofit.create(PokemonApiService::class.java)
        //Buscando a un pokemon en particular
        val call = apiService.getPokemon("pikachu")

        // Se ejecuta la petición de forma asíncrona
        call.enqueue(object : Callback<Pokemon> {
            //Función si ocurre una petición exitosa
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    //Se guarda el objeto desde la respuesta a la petición
                    val pokemon = response.body()
                    //Si no es nulo se pasa al adapter
                    pokemon?.let {
                        pokemonAdapter.setPokemon(it)
                    }
                }
            }
            // Función si ocurre un error en la petición
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}