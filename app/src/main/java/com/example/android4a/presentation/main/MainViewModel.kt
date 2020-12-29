package com.example.android4a.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entity.User
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.example.android4a.domain.usecase.GetUserUseCase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val createUserUseCase: CreateUserUseCase,
                    private val getUserUseCase: GetUserUseCase
                    ): ViewModel(){

    val loginLiveData : MutableLiveData<LoginStatus> = MutableLiveData()
    val createAccountData : MutableLiveData<createAccountStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser, password)
            val loginStatus = if(user != null){
                LoginSuccess(user.email, user.password)
            } else {
                LoginError
            }

            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }

    fun onClickedCreateAccount(emailUser: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser, password)
            val createAccountStatus = if(user == null){
                createAccountSuccess(emailUser)
            } else{
                createAccountError
            }

            if (createAccountStatus == createAccountSuccess(emailUser)){
                createUserUseCase.invoke(User(emailUser, password))
            }

            withContext(Dispatchers.Main){
                createAccountData.value = createAccountStatus
            }

        }
    }
}