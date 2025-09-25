package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.joueur
import org.example.monstre.IndividuMonstre

class CombatMonstre(
    val joueur: Entraineur,
    val monstreJoueur: IndividuMonstre,
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

    if ( monstreSauvage.pv<=0 )
}
}