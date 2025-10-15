let bouton=document.getElementById('bouton-ajouter');
bouton.addEventListener('click',function(){

  console.log('Click');
  let nomJoueur = document.getElementById('ajout-nom').value;
  let scoreJoueur = document.getElementById('ajout-score').value;

  if (!score_valide(scoreJoueur)) {
    alert("Le score doit être un nombre valide.");
    return; //on envoie un popup tout si le score n'est valide sans prendre en compte le score
  }

  let joueur = {
    nom: nomJoueur,
    score: scoreJoueur
  };

  ajouter_joueur(joueur);
  document.getElementById('ajout-nom').value='';
  document.getElementById('ajout-score').value='';
  document.getElementById('total').textContent=calculer_total();
});

let joueurs=document.getElementById('joueurs');

joueurs.addEventListener('click',function(event){
  console.log('Click sur table');
  if(!event.target.matches('.effacer img')){return;}
  console.log('Click sur croix');
  event.target.parentNode.parentNode.remove(); 
  document.getElementById('total').textContent = calculer_total(); //après avoir supprimé la ligne correspondant à l'image cliqué (parentNode), on recalcule le total pour l'actualiser après suppression
});


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

	document.getElementById('popup-annuler').addEventListener('click',function(){
		let tr=document.querySelector('.edition');
		let popup=document.getElementById('popup');
		popup.style.display='none';
		tr.className='';
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
  
    // met à jour le total après modification
    document.getElementById('total').textContent = calculer_total();
    });
	});

function score_valide(score) {
  return /^[0-9]+$/.test(score);
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
}

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
  croix.src   ='https://moodle.iutv.univ-paris13.fr/img/js/effacer.png';
  effacer.append(croix);
  crayon.src='https://moodle.iutv.univ-paris13.fr/img/bjs/modifier.png';
  modifier.append(crayon);
  nom.textContent=joueur.nom;
  score.textContent=joueur.score;
  effacer.className='effacer';
  modifier.className='modifier';
  nom.className='nom';
  score.className='score';
  ligne.append(effacer);
  ligne.append(modifier);
  ligne.append(nom);
  ligne.append(score);
  document.getElementById('joueurs').append(ligne);

  document.getElementById('total').textContent = calculer_total();
}