import json, sqlite3
from app import app
from app.models.Pokemon import Pokemon
from app.models.PokemonDAOInterface import PokemonDAOInterface

class PokemonJsonDAO(PokemonDAOInterface):
	#DAO DU JSON

	def __init__(self):
		with open( app.static_folder + "/data/pokemons.json" ) as f:
			self.pokemons = json.load(f)
	
	def findAll(self):
		#parcours le json et ajoute a une liste les pokemon castés
		pokemon_instances = list()
		for pokemon in self.pokemons:
			pokemon_instances.append(Pokemon(pokemon))
		return pokemon_instances

	def findByNumber(self, number):
		#trouve un pokemon par son nombre dans le json
		for pokemon in self.pokemons:
			if pokemon["Number"] == number:
				return Pokemon(pokemon)
		return []

	def findByType(self, ptype):
		#trouve tous les pokemons avec un type spécifique
		pokemon_instances = list()
		for pokemon in self.pokemons:
			if pokemon["Type_1"] == ptype:
				pokemon_instances.append(Pokemon(pokemon))
		return pokemon_instances


class PokemonSqliteDAO(PokemonDAOInterface):
	#DAO DE SQLITE (base de données)

	def __init__(self):
		self.databasename = app.root_path + '/database.db'
		
	def getDbConnection(self):
		#connexion à la base de données et lignes renvoyées
		conn = sqlite3.connect(self.databasename)
		conn.row_factory = sqlite3.Row
		return conn

	def findAll(self):
		#trouve tous les pokemons en se connectant la bdd puis la requete SQL select *
		conn = self.getDbConnection()
		pokemons = conn.execute('SELECT * FROM pokemons').fetchall()
		pokemon_instances = list()
		for pokemon in pokemons:
			pokemon_instances.append(Pokemon(dict(pokemon)))
		conn.close()
		return pokemon_instances

	def findByNumber(self, number):
		#trouve un pokemon par son nombre avec la requete SELECT ... WHERE number=... """
		conn = self.getDbConnection()
		print(number, type(number))
		pokemons = conn.execute("SELECT * FROM pokemons WHERE Number = ?", (str(number),) )
		for pokemon in pokemons:
			if pokemon["Number"] == number:
				conn.close()
				return Pokemon(dict(pokemon))
		conn.close()
		return pokemons[0]

	def findByType(self, ptype: str):
		#trouve un pokemon par son type avec la requete SELECT ... 	WHERE Type_1=...
		#renvoie une liste des pokemons car il yen a plusieurs du meme type
		conn = self.getDbConnection()
		pokemons = conn.execute("SELECT * FROM pokemons WHERE Type_1 = ?", (ptype,))
		pokemon_instances = list()
		for pokemon in pokemons:
			if pokemon["Type_1"] == ptype:
				pokemon_instances.append(Pokemon(dict(pokemon)))
		conn.close()
		return pokemon_instances