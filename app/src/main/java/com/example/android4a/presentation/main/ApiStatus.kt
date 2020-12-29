package com.example.android4a.presentation.main

import com.example.android4a.presentation.model.FinalFantasy

sealed class ApiStatus

data class ApiStatusSuccess(val FFList : List<FinalFantasy>) : ApiStatus()
object ApiStatusError : ApiStatus()