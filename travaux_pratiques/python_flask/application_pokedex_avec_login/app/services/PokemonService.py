
# import du DAO avec un alias pour simplifier le reste du code de ce fichier
# from app.models.PokemonDAO import PokemonJsonDAO as PokemonDAO
from app.models.PokemonDAO import PokemonSqliteDAO as PokemonDAO

class PokemonService():
	"""
	Class dedicated for the logic behind the pokemons
	"""
	def __init__(self):
		# DAO pour le JSON
		self.pdao = PokemonDAO()

	def getPokemonByNumber(self, num):
		res = self.pdao.findByNumber(num)
		if type(res) is not list: 
			res = [res] 
		return res
	#on recupere le pokemon ayant le numero passe en parametre avec findByNumber
	#et on le met dans une liste si ce n'est pas déjà le cas, on retourne ensuite cette liste

	def getPokemonTypes(self):
		types = set()
		for pokemon in self.pdao.findAll():

			types.add(pokemon.type1)

		return list(types)
	#on retourne une liste de tous les types de pokemon existant avec findAll
	
	def getPokemonsByType(self, ptype):
		res = self.pdao.findByType(ptype)
		if len(res) > 0: 
			return res
		return [{}]
	#on recupere la liste de tous les pokemon au type passé en parametre avec findByType
	#si cette liste n'est pas vide, on la renvoie, sinon on renvoie la liste d'un dictionnaire vide