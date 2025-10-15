let bouton=document.getElementById('bouton-ajouter');
bouton.addEventListener('click',function(){

  console.log('Click');
  let nomJoueur = document.getElementById('ajout-nom').value;
  let scoreJoueur = document.getElementById('ajout-score').value;
  //on recupère les valeurs entrées par l'utilisateur
	
  if (!score_valide(scoreJoueur)) {
    alert("Le score doit être un nombre valide.");
    return; //on envoie un popup tout si le score n'est valide sans prendre en compte le score
  }

  let joueur = {
    nom: nomJoueur,
    score: scoreJoueur
  }; 

  ajouter_joueur(joueur); //on crée un objet joueur à partir de ces valeurs et on l'ajoute au tableau (fonction "ajouter_joueur" plus bas)
  document.getElementById('ajout-nom').value='';
  document.getElementById('ajout-score').value='';
  document.getElementById('total').textContent=calculer_total();
}); //après avoir ajouté le joueur, on supprime ce que l'utilisateur a rempli dans le champ en cas d'ajout de nouveau joueur puis on calcule le nouveau total

let joueurs=document.getElementById('joueurs');

joueurs.addEventListener('click',function(event){
  console.log('Click sur table');
  if(!event.target.matches('.effacer img')){return;}
  console.log('Click sur croix');
  event.target.parentNode.parentNode.remove(); 
  document.getElementById('total').textContent = calculer_total(); 
});
/*après avoir cliqué sur une croix rouge, on supprime la ligne correspondant à l'image cliqué (parentNode) 
puis on recalcule le total pour l'actualiser après suppression*/

joueurs.addEventListener('click',function(event){
	if(!event.target.matches('.modifier img')){return;}
	console.log('Click sur modifier');
	let tr=event.target.parentNode.parentNode;
	let nom  =tr.querySelector('.nom'  ).textContent;
	let score=tr.querySelector('.score').textContent;
	console.log(nom,score);
	tr.className='edition';
	let popup=document.getElementById('popup');
	popup.style.display='block';
	document.getElementById('popup-nom'  ).value=nom;
	document.getElementById('popup-score').value=score;
	
	/*quand on clique sur le crayon d'une ligne, on recupere la ligne dans la variable tr et son nom et son score
	on fait apparaitre un popup, puis on recuper le nom et le score ecrit dans ce popup*/
	
	document.getElementById('popup-annuler').addEventListener('click',function(){
		let tr=document.querySelector('.edition');
		let popup=document.getElementById('popup');
		popup.style.display='none';
		tr.className='';
	//on ferme le popup si demandé
	});
	
	document.getElementById('popup-ok').addEventListener('click', function () {
    	let tr = document.querySelector('.edition');
    	let nouveau_score = document.getElementById('popup-score').value;
  
    	if (!score_valide(nouveau_score)) { // vérifie si nouveau score valide
      		alert("Le score doit être un nombre valide.");
     	 	return;
    	}
  
    	tr.querySelector('.nom').textContent = document.getElementById('popup-nom').value;
    	tr.querySelector('.score').textContent = nouveau_score;
    	let popup = document.getElementById('popup');
    	popup.style.display = 'none';
    	tr.className = '';
		/*après avoir récupéré les valeurs inscrites par l'utilisateur dans le popup plus haut,
		on modifie les valeurs de la ligne dans le tableau par les valeurs du popup*/
		
    	document.getElementById('total').textContent = calculer_total();
		// met à jour le total après modification
    });
});

function score_valide(score) {
  return /^[0-9]+$/.test(score); //return true si regex contient un ou plusieurs chiffres uniquement
}

function calculer_total()
{
  let total=0;
  let tds=document.querySelectorAll('.score');
  for(let i=0;i<tds.length;i++){
    let score=parseInt(tds[i].textContent);
    total+=score;
  }
  return total;
} //parcourt la liste score du tableau et calcule sa somme

function ajouter_joueur(joueur)
{
  console.log('ajouter_joueur',joueur);
  let ligne   =document.createElement('tr');
  let effacer =document.createElement('td');
  let modifier=document.createElement('td');
  let nom     =document.createElement('td');
  let score   =document.createElement('td');
  let croix   =document.createElement('img');
  let crayon  =document.createElement('img');
	
  // on crée une nouvelle ligne puis les variables necessaires de chaque élément de cette ligne
	
  croix.src   ='https://moodle.iutv.univ-paris13.fr/img/js/effacer.png';
  effacer.append(croix);
  crayon.src='https://moodle.iutv.univ-paris13.fr/img/bjs/modifier.png';
  modifier.append(crayon);
  nom.textContent=joueur.nom;
  score.textContent=joueur.score;
	
  // on remplit les variables de la ligne par les informations renseignées par l'utilisateur

  effacer.className='effacer';
  modifier.className='modifier';
  nom.className='nom';
  score.className='score';

  // on donne à chaque variable une classe pour le CSS et la gestion des différents evenements dans le JS
	
  ligne.append(effacer);
  ligne.append(modifier);
  ligne.append(nom);
  ligne.append(score);
  document.getElementById('joueurs').append(ligne);

  // on ajoute à la ligne toutes les variables avec leurs valeurs renseignées et le nome de leurs classes et on ajoute la ligne au tableu joueurs
	
  document.getElementById('total').textContent = calculer_total();
  // on calcule le nouveau total

}
