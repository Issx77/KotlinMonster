package org.example.item

class Badge(
    id: Int,
    nom: String,
    description: String,
    val champion: String
) : Item(id, nom, description)
