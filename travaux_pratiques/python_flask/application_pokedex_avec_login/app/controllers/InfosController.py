from flask import render_template, redirect, url_for, request
from app import app
from app.controllers.LoginController import reqlogged

class InfosController:

    @app.route('/infos', methods = ['GET'])
    @reqlogged
    def infos():
        metadata = {"title":"😌Infos", "pagename": "infos"}
        return render_template('infos.html', metadata = metadata, data = {"name": "Gaël", "favoriteFruit": "🍉"})



