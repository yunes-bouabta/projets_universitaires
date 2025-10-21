import sqlite3
from app import app
from app.models.UserDAOInterface import UserDAOInterface
from app.models.User import User
import bcrypt

class UserSqliteDAO(UserDAOInterface):

    def __init__(self):
        self.databasename=app.root_path+"database.db"
        self._initTable()
    #crée la base de données dans le fichier database de l'app, avec la méthode initTable

    def _getDbConnection(self):
        conn=sqlite3.connect(self.databasename)
        conn.row_factory=sqlite3.Row
        return conn
    #connexion a la base de données puis lignes renvoyées
    
    def _initTable(self):
        conn=self._getDbConnection()
        conn.execute("""
        CREATE TABLE IF NOT EXISTS users(
           id INTEGER PRIMARY KEY,
           username TEXT UNIQUE NOT NULL,
           PASSWORD TEXT NOT NULL,
           role TEXT NOT NULL DEFAULT 'lecteur'
        )"""
        )
        conn.commit()
        conn.close()
    #script création de la base de données après s'y être connectés
    
    def _generatePwdHash(self, password):
        password_encode=password.encode()
        bcrypt.hashpw(password_encode, bcrypt.gensalt())
        return password_encode.decode()
    #hachage de mot de passe en utilisant les methodes salt, hashpw de la libraire bcrypt
    
    def createUser(self, username, password, role='lecteur'):
        conn=self._getDbConnection()
        mdp_hache=self._generatePwdHash(password)
        try:
            conn.execute(
                "INSERT INTO users(username, password, role) VALUES (:username, :password, :role)",
                {"username":username, "password":mdp_hache,"role":role}
            )
        except:
            conn.close()
            return False
        conn.commit()
        conn.close()
        return True
    #creation d'utilisateur dans la bdd avec les valeurs passées en parametre
    #on hache le mdp passé en parametre et on attitre chacune de ces valeurs a une ligne dans la bdd (INSERT INTO ... VALUES...)
    
    def findAll(self):
        conn = self.getDbConnection()

        users = conn.execute('SELECT * FROM users').fetchall()
        users_instances = list()
		
        for user in users:

            users_instances.append(User(dict(user)))
        conn.close()
        return users_instances
    #trouve tous les utilisateurs depuis la requete SQL select * en se connectant la bdd
	#retourne ensuite une liste avec tous les objets de la requete
    
    def findByUsername(self, username):
        conn=self._getDbConnection()
        try:   
            user=conn.execute(
                "SELECT * FROM users WHERE username=:username", username
            )
            conn.close()
            return User(user)
        except:
            conn.close()
            return None
    #connexion a la base de données pour executer SELECT ... WHERE Number=...
    #on renvoie l'objet user casté en objet User
        
    def verifyUser(self, username, password):
        conn=self._getDbConnection()
        try:
            utilisateur=self.findByUsername(username)
        except:
            return 'utilisateur introuvable'
        mdp_bdd=utilisateur.password
        mdp_param=password.encode()
        if bcrypt.checkpw(mdp_bdd, mdp_param):
            conn.close()
            return User(utilisateur)
        else:
            conn.close()
            return None
    # verification si utilisateur existant la bdd :
    # on cherche son nom d'utilisateur, s'il existe, 
    # on encode le mdp en parametre pour le comparer a celui dans la bdd
    # s'ils correspondent, on renvoie l'objet User ayant le nom d'utilisateur et mdp en paramètre

