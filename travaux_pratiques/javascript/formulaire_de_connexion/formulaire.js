let inputNom=document.querySelector('#nom');
let inputLogin=document.querySelector('#login');
let inputMDP=document.querySelector('#mdp');
let inputConfirmation=document.querySelector('#confirmation');
let inputEmail=document.querySelector("#email");
/*on déclare des variables pour chaque champ que l'utilisateur va remplir
on ajoutera a chacune de ces variables un gestionnaire d'evenements pour verifier les expressions regulières (regex)*/

let nbCaractereMdpMini=6;
/*variable definie pour le nombre de caractere minimum que doit comporter un mot de passe
on le met dans une variable pour pouvoir le changer si on le souhaite à l'avenir*/

inputNom.addEventListener('keyup',function(event) 
{
   console.log('Touche nom relâchée');
   let texte=inputNom.value;
   texte=texte.toLowerCase(); //variable dediée au login qui sera en minuscule
   texte=texte.replace(/[^a-z]/g,'-'); //si un caractere n'est pas une lettre, on le remplace par un tiret -
   inputLogin.value=texte; //le champ login se remplit automatiquement avec la variable texte comportant le nom écrit
   let erreur=document.querySelector('#erreur-nom');
   if(/^[a-zA-Z' ]+$/.test(inputNom.value)){erreur.style.opacity=0;}
   else{erreur.style.opacity=1;}
   //si le nom écrit est composé que de lettre, on cache le message d'erreur, sinon on le fait apparaitre
});

inputMDP.addEventListener('keyup',function(event) 
{
   console.log('Touche mot de passe relâchée');
   let erreur=document.querySelector('#erreur-mdp');
   if(inputMDP.value.length<nbCaractereMdpMini){erreur.style.opacity=1;}
   else{erreur.style.opacity=0;} // si la longueur du mdp est inferieure au nombre de caractere mini, on affiche le message d'erreur
   let erreurConfirmation=document.querySelector('#erreur-confirmation');
   if(inputMDP.value==inputConfirmation.value){erreurConfirmation.style.opacity=0;}
   else {erreurConfirmation.style.opacity=1;} 
   /* on demande de confirmer le mdp en le réécrivant une deuxieme fois dans le champ de confirmation 
   si le mdp et le champ de confirmation sont égaux, on cache le message d'erreur sinon on l'affiche*/
});

inputConfirmation.addEventListener('keyup',function(event) 
{
   console.log('Touche confirmation relâchée');
   let erreurConfirmation=document.querySelector('#erreur-confirmation');
   if(inputMDP.value==inputConfirmation.value){erreurConfirmation.style.opacity=0;}
   else {erreurConfirmation.style.opacity=1;}
   /* on demande de confirmer le mdp en le réécrivant une deuxieme fois dans le champ de confirmation 
   si le mdp et le champ de confirmation sont égaux, on cache le message d'erreur sinon on l'affiche*/
});

inputEmail.addEventListener('keyup', function(event){
   console.log('Touche email relâchée');
   let erreur=document.querySelector('#erreur-email');
   if(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(inputEmail.value)){erreur.style.opacity=0;}
   else{erreur.style.opacity=1;}
   /*on teste si la valeur entrée dans le champ email correpond à une chaine de caractère composée possiblement de lettres, chiffres, points (yunes.bouabta),
   suivi d'un @ (@), suivi de lettres ou chiffres (gmail, yopmail...), suivi d'un point (.), suivi de 2 ou + lettres (fr, com, outlook...)*/
});