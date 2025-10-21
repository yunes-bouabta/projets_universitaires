from flask import render_template, redirect, url_for, request
from app import app
import json
from app.services.PokemonService import PokemonService
from app.controllers.LoginController import reqlogged

ps = PokemonService()


class IndexController:

    @app.route("/", methods = ['GET'])
    @reqlogged
    #grace au decorateur reqlogged, la page index ne s'affiche quaux users connectés
    def index():

        pokemon_types = ps.getPokemonTypes()

        data = {
            "pokemontypes": list(pokemon_types)
        }

        metadata = {"title": "🏠Pokedex", "pagename": "index"}

        # permet d'utiliser le template jinja "index.html" 
        # avec les données data dans la variable data (ce nom sera alors utilisé dans le template)
        return render_template('index.html', metadata = metadata, data = data)
    


    



