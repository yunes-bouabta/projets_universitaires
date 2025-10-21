import math

class Pokemon:

    def __init__(self, dico):
        self.number = dico["Number"]
        self.name = dico["Name"]
        self.type1 = dico["Type_1"]
        self.generation = dico["Generation"]
        self.attack = dico["Attack"]
        self.defense = dico["Defense"]
        self.sp_atk = dico["Sp_Atk"]
        self.sp_def = dico["Sp_Def"]
        self.speed = dico["Speed"]
        self.hp = dico["HP"]
    #initialisation objet pokemon depuis json

    def getGlobalDefense(self):
        # calcul sans justification pour manipuler données
        return (self.defense + self.sp_def) * (math.log(self.hp + self.speed))

    def getGlobalAttack(self):
        return (self.attack + self.sp_atk) * (math.log(self.speed))


