from app import app

class UserDAOInterface:
    #Squelette des methodes du DAO user

    def createUser(self, username,password, role='lecteur'):
        pass

    def findByUsername(self, username):
        pass

    def verifyUser(self, username, password):
        pass

    def findAll(self):
        pass