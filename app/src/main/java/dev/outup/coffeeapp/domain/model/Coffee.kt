package dev.outup.coffeeapp.domain.model

import dev.outup.coffeeapp.domain.enums.CoffeeSize
import dev.outup.coffeeapp.domain.model.option.Option

class Coffee(var id: String, val itemName: String, val size: CoffeeSize, val options: List<Option>) : DomainModel {
}