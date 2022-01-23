package com.flaringapp.elevator_core.person_factory

import kotlin.random.Random

interface PersonNameFactory {

    fun createName(): String

}

class DefinedPersonNameFactory : PersonNameFactory {

    override fun createName(): String {
        val isMale = Random.nextBoolean()
        return if (isMale) males.random()
        else females.random()
    }

    private val males = listOf(
        "Liam",
        "Noah",
        "Jackson",
        "Aiden",
        "Elijah",
        "Grayson",
        "Lucas",
        "Oliver",
        "Caden",
        "Mateo",
        "Muhammad",
        "Mason",
        "Carter",
        "Jayden",
        "Ethan",
        "Sebastian",
        "James",
        "Michael",
        "Benjamin",
        "Logan",
        "Leo",
        "Luca",
        "Alexander",
        "Levi",
        "Daniel",
        "Josiah",
        "Henry",
        "Jace",
        "Julian",
        "Jack",
        "Ryan",
        "Jacob",
        "Asher",
        "Wyatt",
        "William",
        "Owen",
        "Gabriel",
        "Miles",
        "Lincoln",
        "Ezra",
        "Isaiah",
        "Luke",
        "Cameron",
        "Caleb",
        "Isaac",
        "Carson",
        "Samuel",
        "Colton",
        "Maverick",
        "Matthew",
    )

    private val females = listOf(
        "Sophia",
        "Olivia",
        "Riley",
        "Emma",
        "Ava",
        "Isabella",
        "Aria",
        "Aaliyah",
        "Amelia",
        "Mia",
        "Layla",
        "Zoe",
        "Camilla",
        "Charlotte",
        "Eliana",
        "Mila",
        "Everly",
        "Luna",
        "Avery",
        "Evelyn",
        "Harper",
        "Lily",
        "Ella",
        "Gianna",
        "Chloe",
        "Adalyn",
        "Charlie",
        "Isla",
        "Ellie",
        "Leah",
        "Nora",
        "Scarlett",
        "Maya",
        "Abigail",
        "Madison",
        "Aubrey",
        "Emily",
        "Kinsley",
        "Elena",
        "Paisley",
        "Madelyn",
        "Aurora",
        "Peyton",
        "Nova",
        "Emilia",
        "Hannah",
        "Sarah",
        "Ariana",
        "Penelope",
        "Lila",
    )
}