package com.example.android4a.presentation.main

sealed class createAccountStatus

data class createAccountSuccess(val email: String) : createAccountStatus()
object createAccountError: createAccountStatus()
