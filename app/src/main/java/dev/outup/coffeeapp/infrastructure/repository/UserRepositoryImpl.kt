package dev.outup.coffeeapp.infrastructure.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import dev.outup.coffeeapp.domain.model.User
import dev.outup.coffeeapp.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await

object UserRepositoryImpl : RepositoryImplCommon<User>, UserRepository {
    private val collection: CollectionReference = db.collection("users")

    override fun beforeSave(data: User): HashMap<String, Any> {
        return hashMapOf(
            "userName" to data.userName
        )
    }

    override fun afterLoad(documentId: String, document: MutableMap<String, Any>): User {
        val userName: String = document["userName"] as String
        return User(documentId, userName)
    }

    override suspend fun loadById(documentId: String): User? {
        Log.d(TAG, "UserRepositoryImpl::loadById start.")
        val documentReference: DocumentReference = collection.document(documentId)
        val data: MutableMap<String, Any>? = documentReference.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
            } else {
                Log.d(TAG, "No such document... ID : $documentId")
            }
        }.addOnFailureListener { error ->
            Log.d(TAG, "get failed with ", error)
        }.await().data
        return if (data == null) {
            null
        } else {
            afterLoad(documentId, data)
        }
    }

    override fun save(user: User) {
        val document = beforeSave(user)
        collection.document(user.id).set(document)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully written!")
                Log.d(TAG, "DocumentSnapshot data: $document")
            }
            .addOnFailureListener { error -> Log.w(TAG, "Error writing document", error) }
    }
}