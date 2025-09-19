package org.example.monde

import org.example.monstre.EspeceMonstre

/**
 * Représente une zone géographique du monde (route, caverne, mer...).
 * Une zone peut contenir plusieurs espèces de monstres sauvages.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Niveau d'expérience recommandé pour cette zone.
 * @property especesMonstres Liste des espèces de monstres présentes dans cette zone.
 * @property zoneSuivante Référence vers la zone suivante (ou null s'il n'y en a pas).
 * @property zonePrecedente Référence vers la zone précédente (ou null s'il n'y en a pas).
 */
class Zone(
    val id: Int,
    val nom: String,
    val expZone: Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    // TODO : faire la méthode genereMonstre()

    // TODO : faire la méthode rencontreMonstre()
}