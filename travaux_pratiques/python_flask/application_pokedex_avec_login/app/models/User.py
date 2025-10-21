from app import app

class User:
    
    def __init__(self, id, username, role):
        self.id=id
        self.username=username
        self.role=role

    #initialise l'objet user