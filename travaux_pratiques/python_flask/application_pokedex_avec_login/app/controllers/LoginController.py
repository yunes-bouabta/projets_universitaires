from os import abort
from flask import render_template, redirect, url_for, request
# imports nécessaires pour la connexion
from flask import session, flash
from app import app
from functools import wraps

def reqlogged(f):
	@wraps(f)
	def wrap(*args, **kwargs):
		if 'logged' in session:
			return f(*args, **kwargs)
		else:
			flash('Denied. You need to login.') # message flash
			return redirect(url_for('login'))
	return wrap
#creation decorateur reqlogged qui, si la clé 'logged' est dans le dictionnaire session,
#renvoie la fonction et ses arguments qui va suivre
#sinon, elle renvoie vers la page de login

def reqrole(role):
	def wrap(f):
		@wraps(f)
		def verifyRole(*args, **kwargs):
			if 'logged' not in session:
				flash("You need to login")
				return redirect(url_for('login'))
			else:
				if session['role']!=role:
					abort(403)
				else:
					return f(*args, **kwargs) 
		return verifyRole
	return wrap	
#creation decorateur reqrole qui, si la clé 'logged' n'est pas dans le dictionnaire session, renvoie vers login
#sinon si le role de la session est different de celui en parametre du decorateur, renvoie l'erreur 403 (pas la permission)
#sinon si le role correspond, elle renvoie la fonction et ses arguments qui suit

class LoginController:

	@app.route('/login', methods=['GET', 'POST'])
	def login():
		msg_error = None
		if request.method == 'POST':
			if request.form['username'] != 'admin' or request.form['password'] != '1234':
				msg_error = 'Invalid Credentials.'
			else:
				# user connecté
				session["logged"] = True
				# on enregistre le nom de l'utilisateur dans la session, ainsi on peut y accéder dans le template
				session['username'] = request.form['username']
				return redirect(url_for('index'))
		else:
			if session.get("username", "") == "admin":
				return redirect(url_for('index'))
		return render_template('login.html', msg_error=msg_error)
	#partie pratique ou seul admin et 1234 etait accepte comme user, desormais c'est avec la bdd

	@app.route('/signin', methods=['GET', 'POST'])
	def signin():
		msg_error=None
		return render_template('signin.html', msg_error=msg_error)
	#si la route envoyé est signin, depuis le formulaire de connexion (voir template login.html)
	#on renvoie la page signin

	@app.route('/logout')
	@reqlogged
	def logout():
		session.clear()
		flash('Successfully logged out')
		return redirect(url_for('login'))
	#si l'utilisateur clique sur logout, on ferme la session et on le renvoie vers la page login


