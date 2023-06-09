package com.tunahanozatac.cryptoapps.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tunahanozatac.cryptoapps.util.Constants.FAVOURITES_COLLECTION
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.data.mappers.toFavouriteUI
import com.tunahanozatac.cryptoapps.domain.model.CoinDetailUI
import com.tunahanozatac.cryptoapps.domain.model.FavouritesUI
import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticator constructor(
    private val firebaseAuth: FirebaseAuth, private val firebaseFirestore: FirebaseFirestore
) : Authenticator {

    override fun getFirebaseUserUid() = flow {
        firebaseAuth.currentUser?.uid?.let {
            emit(it)
        }
    }

    override fun signUpWithEmailAndPassword(
        email: String, password: String
    ) = flow {

        emit(Resource.Loading)
        emit(Resource.Success(firebaseAuth.createUserWithEmailAndPassword(email, password).await()))
    }.catch {
        emit(Resource.Error(it))
    }

    override fun signInWithEmailAndPassword(
        email: String, password: String
    ) = flow {

        emit(Resource.Loading)

        emit(Resource.Success(firebaseAuth.signInWithEmailAndPassword(email, password).await()))
    }.catch {
        emit(Resource.Error(it))
    }

    override fun isCurrentUserExist() = flow {
        emit(firebaseAuth.currentUser != null)
    }

    override fun getCurrentUser() = flow {
        emit(Resource.Loading)
        firebaseAuth.currentUser?.let {
            emit(Resource.Success(it))
        }
    }.catch {
        emit(Resource.Error(it))
    }

    override fun signOut() = firebaseAuth.signOut()

    override fun addToFavourites(coinDetailUI: CoinDetailUI) = flow {
        emit(Resource.Loading)
        getFirebaseUserUid().collect {
            val favRef =
                firebaseFirestore.collection(FAVOURITES_COLLECTION).document(it).collection("coins")
                    .document(coinDetailUI.toFavouriteUI().name.orEmpty())
                    .set(coinDetailUI.toFavouriteUI())
            favRef.await()
            emit(Resource.Success(favRef))
        }
    }.catch {
        emit(Resource.Error(it))
    }

    override fun getFavourites() = flow {
        emit(Resource.Loading)
        getFirebaseUserUid().collect {
            val snapshot =
                firebaseFirestore.collection(FAVOURITES_COLLECTION).document(it).collection("coins")
                    .get().await()
            val data = snapshot.toObjects(FavouritesUI::class.java)
            emit(Resource.Success(data))
        }
    }.catch {
        emit(Resource.Error(it))
    }

    override fun deleteFromFavourites(favouritesUI: FavouritesUI) = flow {
        emit(Resource.Loading)
        getFirebaseUserUid().collect {
            val favRef =
                firebaseFirestore.collection(FAVOURITES_COLLECTION).document(it).collection("coins")
                    .document(favouritesUI.name.orEmpty()).delete()
            favRef.await()
            emit(Resource.Success(favRef))
        }

    }.catch {
        emit(Resource.Error(it))
    }
}