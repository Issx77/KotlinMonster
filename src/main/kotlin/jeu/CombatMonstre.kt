package org.example.jeu
import org.example.item.Item
import org.example.dresseur.Entraineur
import org.example.item.Utilisable
import org.example.joueur
import org.example.monstre.IndividuMonstre

class CombatMonstre(
    val joueur: Entraineur,
    var monstreJoueur: IndividuMonstre,
    val monstreSauvage: IndividuMonstre
){
    var round: Int = 1

fun gameOver(): Boolean{
    for (monstre in joueur.equipeMonstre) {
        if (monstre.pv > 0) {
            return false
        }
    }
    return true
}

fun joueurGagne(): Boolean{

    if ( monstreSauvage.pv<=0 ){
        println("${joueur.nom} a gagné!!")
        val gainExp= monstreSauvage.exp*0.20
        monstreJoueur.exp=monstreJoueur.exp+gainExp
        println("${monstreJoueur.nom} gagne ${gainExp} exp")
        return true
    }
    else{
        if (monstreSauvage.entraineur==joueur){
            println("${monstreSauvage.nom} a été capturé !")
            return true

        }
        else{return false}

    }
}
fun actionAdversaire(){
    if (monstreSauvage.pv>0){
        monstreSauvage.attaquer(monstreJoueur)
    }
}
fun actionJoueur(): Boolean{
    if (gameOver()){
        return false
    }
    else{
        println("menu choix d'actions :" +
                "/t 1 -> Attaquer /n" +
                "/t 2 -> utiliser un objet/n" +
                "/t 3 -> changer de monstre")
        var choixAction = readln()
        while ( choixAction.toInt() !in (1..3)){
            println("Veuillez saisir une action valable")
            choixAction = readln()
        }
        if (choixAction.toInt()==1){
            monstreJoueur.attaquer(monstreSauvage)
            //return true
        }
        else if (choixAction.toInt()==2){//choix objet
            for ((index,objet) in this.joueur.sacItems.withIndex()){
                println("$index => ${objet}")
            }
            var choixObjet = readln()
            while ( choixObjet.toInt() !in (0..joueur.sacItems.size-1)){
                println("Veuillez saisir une action valable")
                choixObjet = readln()
            }
            val obj= joueur.sacItems[choixObjet.toInt()]
            if(obj is Utilisable){
                val result= obj.utiliser(monstreSauvage)
                if (result){
                    return false
                }
            }else{println("Objet non utilisable")}

        }
        else{//changer de monstre
            for ((index,monster) in this.joueur.equipeMonstre.withIndex()){
                println("$index => ${monster}")
            }
            var choixMonster = readln()
            while ( choixMonster.toInt() !in (0..joueur.equipeMonstre.size-1)){
                println("Veuillez saisir un monstre encore en vie")
                choixMonster = readln()
            }
           val autreMonstre =joueur.equipeMonstre[choixMonster.toInt()]
            if (autreMonstre.pv <=0  ){
                println("Impossible ! Ce monstre est KO")
            }
            else{
                println("${autreMonstre} remplace ${monstreJoueur}")
                monstreJoueur=autreMonstre
            }
        }
        return true

    }
}
fun afficherCombat(){
    println("============== Début Du Round : $round ==============\n"+
            "            Niveau : ${monstreSauvage.niveau}\n"+
            "            Pv: ${monstreSauvage.pv}/${monstreSauvage.pvMax}\n"+
            monstreSauvage.espece.afficheArt(true)+
            monstreJoueur.espece.afficheArt(false)+
            "           Niveau : ${monstreJoueur.niveau}\n" +
            "           Pv: ${monstreJoueur.pv}/${monstreJoueur.pvMax}\n",
        )


}
fun jouer(){
   val joueurPlusRapide = monstreJoueur.vitesse > monstreSauvage.vitesse
    afficherCombat()
    if (joueurPlusRapide==true){
        val continuer=actionJoueur()
        if (continuer==false){
            return
        }
        actionAdversaire()
    }
    else{
        actionAdversaire()
        if (gameOver()==false){
            val continuer=actionJoueur()
            if (continuer==false){
                return
            }

        }
    }

}/**
    * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
    *
    * Affiche un message de fin si le joueur perd et restaure les PV
    * de tous ses monstres.
    */
fun lanceCombat(){


        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }

}