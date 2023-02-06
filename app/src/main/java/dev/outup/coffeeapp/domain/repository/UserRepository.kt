package dev.outup.coffeeapp.domain.repository

import dev.outup.coffeeapp.domain.model.User

interface UserRepository {
    suspend fun loadById(documentId: String): User?
    fun save(user: User)
}