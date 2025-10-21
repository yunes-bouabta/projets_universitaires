from flask import render_template, redirect, url_for, request
from app import app
import json
from app.services.PokemonService import PokemonService
from app.controllers.LoginController import reqlogged


ps = PokemonService()

class PokemonController:
	"""
	Classe dédiée au contrôle des accès liés aux Pokémons
	"""

	@app.route('/pokemon/<int:pokedex_id>', methods = ['GET'])
	@reqlogged
	def pokemonView(pokedex_id):
		
		# obtention d'un pokemon par son numéro
		pokemons = ps.getPokemonByNumber(pokedex_id)
		
		metadata = {"title": pokemons[0].name}

		return render_template('pokemon.html', metadata = metadata, pokemons = pokemons)


	@app.route('/pokemon', methods=["POST"])
	@reqlogged
	def pokemonByIndex():
		# recuperation de l'id rempli dans le formulaire
		pokedex_id = int(request.form['index'])
		
		# obtention d'un pokemon par son numéro 
		pokemons = ps.getPokemonByNumber(pokedex_id)
		
		metadata = {}
		if len(pokemons) != 1: 
			metadata["title"] = "results"
		else: 
			metadata["title"] = pokemons[0].name
			#titre de la page = nom du pokemon (jinja)
		
		return render_template('pokemon.html', metadata= metadata, pokemons = pokemons)
	#on renvoie la page affichant le pokemon trouvé


	@app.route("/pokemon/type", methods=["GET"])
	@reqlogged
	def pokemonsByType():
		# on récupére les valeurs à partir des parametres GET du formulaire
		ptype = request.args.get('pokemontype')

		# on recupere les pokémons ayant le type demandé
		pokemons_by_type = ps.getPokemonsByType(ptype)

		metadata = {"title": ptype}
		#le titre de la page est le type de pokemon choisi
		return render_template('pokemon.html', metadata=metadata, pokemons = pokemons_by_type)
	#on renvoie la page avec tous les pokemons ayant le type voulu
