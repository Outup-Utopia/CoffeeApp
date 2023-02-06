package dev.outup.coffeeapp.domain.model.option

import dev.outup.coffeeapp.domain.model.DomainModel

interface Option : DomainModel {
}

enum class Appendable(itemName: String) : Option {
    SOURCE("source"),
    POWDER("powder"),
    HONEY("honey")
}

enum class Amount(description: String) {
    DOUBLE("double"),
    HALF("half"),
    NONE("none")
}

enum class AmountMutable(itemName: String) : Option {
    WHIP("whip"),
    CHOCOLATE_CHIP("chocolate chip"),
    FOAM_MILK("foam milk"),
    ICE("ice");

    var amount: Amount? = null
        set(value) {
            amount = value
        }
}