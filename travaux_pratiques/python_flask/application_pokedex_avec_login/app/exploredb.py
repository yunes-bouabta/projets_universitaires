import sqlite3

connection = sqlite3.connect('database.db')
connection.row_factory = sqlite3.Row
pokemons = connection.execute("SELECT * FROM pokemons WHERE name = 'Eevee'").fetchall()
connection.close()
for pokemon in pokemons: 
    print(dict(pokemon))

#pratique, prise de main de sqlite





