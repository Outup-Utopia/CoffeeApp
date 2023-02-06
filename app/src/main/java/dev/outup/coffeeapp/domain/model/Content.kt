package dev.outup.coffeeapp.domain.model

import com.google.firebase.Timestamp

class Content(
    val id: String?,
    val title: String?,
    val imageLocation: String?,
    val coffee: Coffee?,
    val createdAt: Timestamp?,
    val updatedAt: Timestamp?
) :
    DomainModel {
    constructor() : this(null, null, null, null, null, null)
}