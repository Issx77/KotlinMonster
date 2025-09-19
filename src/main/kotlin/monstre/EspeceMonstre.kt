package org.example.monstre

import java.io.File

/**
 * Représente une espèce de monstre.
 *
 * Cette classe regroupe les caractéristiques communes à tous les individus
 * d'une même espèce de monstre (par exemple, un type de monstre spécifique).
 *
 * @property id Identifiant unique de l'espèce.
 * @property nom Nom de l'espèce de monstre.
 * @property type Type élémentaire de l'espèce (ex: feu, eau, plante).
 * @property baseAttaque Valeur de base de l'attaque physique.
 * @property baseDefense Valeur de base de la défense physique.
 * @property baseVitesse Valeur de base de la vitesse.
 * @property baseAttaqueSpe Valeur de base de l'attaque spéciale.
 * @property baseDefenseSpe Valeur de base de la défense spéciale.
 * @property basePv Valeur de base des points de vie.
 * @property modAttaque Modificateur (multiplicateur) de l'attaque physique.
 * @property modDefense Modificateur (multiplicateur) de la défense physique.
 * @property modVitesse Modificateur (multiplicateur) de la vitesse.
 * @property modAttaqueSpe Modificateur (multiplicateur) de l'attaque spéciale.
 * @property modDefenseSpe Modificateur (multiplicateur) de la défense spéciale.
 * @property modPv Modificateur (multiplicateur) des points de vie.
 * @property description Description textuelle de l'espèce.
 * @property particularites Particularités ou capacités spéciales de l'espèce.
 * @property caracteres Traits de caractère ou comportements typiques.
 */

class EspeceMonstre (
    var id : Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caracteres: String = "",
) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back";
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}