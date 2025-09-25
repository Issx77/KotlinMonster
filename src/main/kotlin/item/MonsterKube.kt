package org.example.item

import org.example.joueur
import org.example.monstre.IndividuMonstre
import kotlin.random.Random
import kotlin.uuid.Uuid.Companion.random

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
) : Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous avez lancer une MonsterKube !");
        if (cible.entraineur != null) {
            println("Le monstre ne peut pas être capturé.");
            return false;
        } else {
            val ratioVie = cible.pv / cible.pvMax;
            var chanceEffective = chanceCapture * (1.5 - ratioVie);
            if (chanceEffective <= 5.0) {
                chanceEffective = 5.0;
            }

            val nbAleatoire = (0..100).random();
            //Random().nextDouble(0.0,2.1)
            if (nbAleatoire < chanceEffective) {
                println("le monstre est capturé")
                cible.renommer()
                if (joueur.equipeMonstre.size >= 6) {
                    joueur.boiteMonstre.add(cible)

                }
                else{
                    joueur.equipeMonstre.add(cible)
                }
                cible.entraineur=joueur
                return true
            }
            else{
                println("Presque § Le Kube n'a pas pu capturer le monstre! ")
                return false
            }



        }
    }
}