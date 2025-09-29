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
    for (monstre in joueur.equipeMonstre){
        if (monstre.pv>0){
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
    if (gameOver()==true){
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
            return true
        }
        else if (choixAction.toInt()==2){
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
                if (result==true){
                    return false
                }
            }else{println("Objet non utilisable")}

        }

    }
}
}