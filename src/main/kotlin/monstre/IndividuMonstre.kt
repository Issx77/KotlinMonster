package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.random.Random

class IndividuMonstre (
   val id: Int,
   val nom: String,
   val espece: EspeceMonstre,
   val entraineur: Entraineur?,
   expInit: Double

) {
    var niveau: Int = 1

    //attaque monstre
    var attaque: Int = espece.baseAttaque + Random.nextInt(from = -2, until = 2)
    var defense: Int = espece.baseDefense + Random.nextInt(from = -2, until = 2)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(from = -2, until = 2)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(from = -2, until = 2)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(from = -2, until = 2)
    var pvMax: Int = espece.basePv + Random.nextInt(from = -5, until = 5)
    var potentiel: Double = Random.nextDouble(from = 0.5, until = 2.0)

    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv.coerceIn(0,pvMax)
        }
}