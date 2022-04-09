package com.example.JTInsurance.viewmodels

/*import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.JTInsurance.models.User
import com.example.JTInsurance.persistance.AppDatabase
import com.example.JTInsurance.persistance.Repository
import kotlinx.coroutines.*

open class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    // LiveData gives us updated users when they change.
    val allUsers: LiveData<List<User>>?
    private val _userFromDb: MutableLiveData<User> = MutableLiveData<User>()
    var userFromDb: LiveData<User>? = _userFromDb

    init {
        val dao = AppDatabase.getDatabaseInstance(application)?.userDao()
        repository = Repository(dao)
        allUsers = repository.allUsers
    }

     fun register(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
                repository.registerUser(user)
            }
        }

    suspend fun login(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
           val user = repository.loginUser(user)
            delay(10000)
            withContext(Dispatchers.Main) {
                _userFromDb.value = user?.value
            }
        }
    }
}*/