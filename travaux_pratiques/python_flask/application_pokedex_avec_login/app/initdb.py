import sqlite3, json

with open( "static/data/pokemons.json" ) as f: pokemons = json.load(f)

connection = sqlite3.connect('database.db')


with open('schema.sql') as f:
    connection.executescript(f.read())

cur = connection.cursor()

for p in pokemons:
	cur.execute("INSERT INTO pokemons (Name, Type_1, Generation, Attack, Defense, Sp_Atk, Sp_Def, Speed, HP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
        (p["Name"], p["Type_1"], p["Generation"], p["Attack"], p["Defense"], p["Sp_Atk"], p["Sp_Def"], p["Speed"], p["HP"])
    )

connection.commit()
connection.close()

#creation et alimentation de la bdd pokemon
#pratique et prise de main de sqlite