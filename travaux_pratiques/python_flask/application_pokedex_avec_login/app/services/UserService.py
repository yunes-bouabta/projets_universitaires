from app.models.UserDAO import UserSqliteDAO as UserDAO

class UserService:

    def __init__(self):
        #DAO pour la Base de données
        self.pdao=UserDAO()

    def getUsers(self):
        return self.pdao.findAll()
    #on renvoie une liste comportant tous les utilisateurs grace a la methode findAll

    def signin(self, username, password):
        return self.pdao.createUser(username,password)
    #on crée un utilistateur avec les parametres passés grace a la methode createUser

    def login(self,username,password):
        return self.pdao.verifyUser(username,password)
    #on permet la connexion en renvoyant l'objet de l'utilisateur
    #ayant comme mdp et nom d'utilisateur ceux passés en parametre

    def getUserByUsername(self,username):
        user_list=list()
        user_list.append(self.pdao.findByUsername(username))
        return user_list
    #on crée une liste où l'on ajoute l'utilisateur trouvé grace à findByUsername 
    #ayant comme nom utilisateur celui passé en parametre puis on renvoie cette liste