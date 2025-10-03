package org.example.jeu
import org.example.item.Item
import org.example.dresseur.Entraineur
import org.example.item.Utilisable
import org.example.joueur
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre
import org.example.monstre1
import org.example.monstre2
import org.example.monstre3


class Partie(var id: Int, var joueur: Entraineur,var zone: Zone ) {
    fun choixStarter(){
        monstre1.afficheDetail()
        monstre2.afficheDetail()
        monstre3.afficheDetail()
        println("choisi ton monstre : " +
                "\n-> 1 Flamkip" +
                "\n-> 2 Aquamy" +
                "\n-> 3 Laoumi")

        do {

            val choixpoke = readln()
            var starter =monstre1
            if (choixpoke.toInt()==1){
                starter=monstre1
                //return true
            }
            else if (choixpoke.toInt()==2){
                starter=monstre2
                //return true
            }
            else if (choixpoke.toInt()==3){
                starter=monstre3
            }
            starter.renommer()
            joueur.equipeMonstre.add(starter)
            starter.entraineur=joueur
        }while ( choixpoke.toInt() !in (1..4))
    }
    fun modifierOrdreEquipe() {
        if (joueur.equipeMonstre.size < 2) {
            println("Impossible de modifier l’ordre, vous avez moins de 2 monstres.")
            return
        }

        println("=== Modifier l’ordre de l’équipe ===")
        joueur.equipeMonstre.forEachIndexed { index, monstre ->
            println("${index + 1}. ${monstre.nom}")
        }

        print("Entrez le numéro du premier monstre à déplacer : ")
        val pos1 = readLine()?.toIntOrNull()?.minus(1)
        print("Entrez la nouvelle position du monstre : ")
        val pos2 = readLine()?.toIntOrNull()?.minus(1)

        if (pos1 != null && pos2 != null &&
            pos1 in joueur.equipeMonstre.indices &&
            pos2 in joueur.equipeMonstre.indices
        ) {
            val tmp = joueur.equipeMonstre[pos1]
            joueur.equipeMonstre[pos1] = joueur.equipeMonstre[pos2]
            joueur.equipeMonstre[pos2] = tmp
            println(" ${tmp.nom} a changé de place avec ${joueur.equipeMonstre[pos1].nom}")
        } else {
            println(" Positions invalides.")
        }
    }
    fun examineEquipe() {
        var continuer = true
        while (continuer) {
            println("\n=== Équipe de ${joueur.nom} ===")
            joueur.equipeMonstre.forEachIndexed { index, monstre ->
                println("${index + 1}. ${monstre.nom} - PV: ${monstre.pv}/${monstre.pvMax} (Niv. ${monstre.niveau})")
            }
            println("\nTapez le numéro du monstre pour voir ses détails.")
            println("Tapez 'm' pour modifier l’ordre de l’équipe.")
            println("Tapez 'q' pour revenir au menu principal.")

            when (val choix = readLine()) {
                "q" -> continuer = false
                "m" -> modifierOrdreEquipe()
                else -> {
                    val num = choix?.toIntOrNull()
                    if (num != null && num in 1..joueur.equipeMonstre.size) {
                        joueur.equipeMonstre[num - 1].afficheDetail()
                    } else {
                        println("Choix invalide.")
                    }
                }
            }
        }
    }
    fun jouer() {
        var continuer = true
        while (continuer) {
            println(" Vous êtes dans la zone : ${zone.nom}")
            println("Que voulez-vous faire ?")
            println("1. Rencontrer un monstre sauvage")
            println("2. Examiner l’équipe")
            println("3. Aller à la zone suivante")
            println("4. Aller à la zone précédente")
            println("5. Quitter le jeu")

            when (readLine()?.toIntOrNull()) {
                1 -> zone.rencontreMonstre()
                2 -> examineEquipe()
                3 -> if(zone.zoneSuivante != null)zone= zone.zoneSuivante!!
                4 -> if (zone.zonePrecedente !=null)zone = zone.zonePrecedente!!
                5 -> {
                    println("Merci d’avoir joué !")
                    continuer = false
                }
                else -> println("Choix invalide.")
            }
        }
    }
}