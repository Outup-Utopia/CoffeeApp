package dev.outup.coffeeapp.domain.usecase

import dev.outup.coffeeapp.domain.model.Content
import dev.outup.coffeeapp.domain.repository.CoffeeRepository
import dev.outup.coffeeapp.domain.repository.ContentRepository

class ContentService(private val contentRepository: ContentRepository, private val coffeeRepository: CoffeeRepository) {
    suspend fun getTimeline(userId: String): ArrayList<Content> {
        val contents = contentRepository.loadAsTimeline(userId)
        val contentModels: ArrayList<Content> = arrayListOf()
        contents.forEach { content ->
            if (content?.id != null) {
                val coffee = coffeeRepository.loadById(content.id!!)
                contentModels.add(
                    Content(
                        content.id,
                        content.title,
                        content.imageLocation,
                        coffee,
                        content.createdAt,
                        content.updatedAt
                    )
                )
            }
        }
        return contentModels
    }

    fun registerContent(userId: String, content: Content) {
        TODO("implement")
    }

    fun updateContent(userId: String, content: Content) {
        TODO("implement")
    }

    fun deleteContent(userId: String, documentId: String) {
        TODO("implement")
    }
}