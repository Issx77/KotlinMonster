package org.example.monstre


import org.example.changeCouleur
import org.example.dresseur.Entraineur
import kotlin.collections.minusAssign
import kotlin.math.pow
import kotlin.random.Random

class IndividuMonstre(
    val id: Int,
    var nom: String,
    val espece: EspeceMonstre,
    var entraineur: Entraineur?,
    expInit: Double
) {

    var niveau: Int = 1

    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 3)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 3)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 3)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 3)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 3)
    var pvMax: Int = espece.basePv + Random.nextInt(-5, 6)
    var potentiel: Double = Random.nextDouble(0.5, 2.0)

    var exp: Double = 0.0
        set(value) {
            field = value
            while (field >= palierExp(niveau)) {
                levelUp()
                println("Le monstre $nom est maintenant niveau $niveau")
            }
        }

    var pv: Int = pvMax
        set(value) {
            field = when {
                value < 0 -> 0
                value > pvMax -> pvMax
                else -> value
            }
        }

    // Initialisation dans init
    init {
        this.exp = expInit
    }

    /** Calcule l'expérience nécessaire pour atteindre un niveau donné */
    fun palierExp(niveau: Int): Double {
        return 100 * (niveau - 1).toDouble().pow(2.0)
    }

    /** Incrémente le niveau et applique les gains */
    fun levelUp() {
        niveau += 1

        val ancienPvMax = pvMax

        attaque += (espece.modAttaque * potentiel).toInt() + Random.nextInt(-2, 3)
        defense += (espece.modDefense * potentiel).toInt() + Random.nextInt(-2, 3)
        vitesse += (espece.modVitesse * potentiel).toInt() + Random.nextInt(-2, 3)
        attaqueSpe += (espece.modAttaqueSpe * potentiel).toInt() + Random.nextInt(-2, 3)
        defenseSpe += (espece.modDefenseSpe * potentiel).toInt() + Random.nextInt(-2, 3)

        val gainPvMax = (espece.modPv * potentiel).toInt() + Random.nextInt(-5, 6)
        pvMax += gainPvMax
        pv += gainPvMax
    }

    fun attaquer(cible: IndividuMonstre) {
        // 1. Calcul des dégâts : attaque - défense (minimum 1)
        val degats = (this.attaque - cible.defense).coerceAtLeast(1)

        // 2. Appliquer les dégâts aux PV de la cible
        cible.pv -= degats  // ta classe gère les bornes min/max

        // 3. Afficher un message avec changement de couleur (si tu veux)
        println(changeCouleur("${this.nom} attaque ${cible.nom} et inflige $degats dégâts !", "rouge"))
        println(changeCouleur("${cible.nom} a maintenant ${cible.pv} PV.", "jaune"))

        // 4. Vérifier si la cible est K.O.
        if (cible.pv == 0) {
            println(changeCouleur("${cible.nom} est K.O. !", "magenta"))
        }
    }
    fun renommer() {
        println("Renommer ${this.nom} : ")
        val nouveauNom = readLine()

        if (!nouveauNom.isNullOrBlank()) {
            nom = nouveauNom
            println("✅ Le monstre a été renommé en ${this.nom}")
        } else {
            println("ℹ️ Nom inchangé, ${nom} conserve son nom.")
        }
    }
    fun afficheDetail() {
        println("===================================")
        println(this.espece.afficheArt())
        println("Nom       : ${this.nom}")
        println("Espèce    : ${this.espece.nom}")
        println("Niveau    : ${this.niveau}")
        println("PV        : ${this.pv}/${this.pvMax}")
        println("Attaque   : ${this.attaque}")
        println("Défense   : ${this.defense}")
        println("Vitesse   : ${this.vitesse}")
        println("AttaqueSp : ${this.attaqueSpe}")
        println("DéfenseSp : ${this.defenseSpe}")
        println("===================================")

        // Ici tu pourras afficher l’art ASCII s’il est stocké dans l’espèce
        // Exemple : println(this.espece.asciiArt)
    }

}




