package com.example.android4a.presentation.main

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.android4a.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    //TODO Navigation deuxième activité

                    val intent = Intent(this@MainActivity, newListActivity::class.java)
                    startActivity(intent)
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Unknown Account")
                        .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
                        .show()
                }
            }
        })

        mainViewModel.createAccountData.observe(this, Observer {
            when(it){
                is createAccountSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Success")
                        .setMessage("Your account has been well created")
                        .setPositiveButton("Nice!"){ dialog, which -> dialog.dismiss() }
                        .show()
                }
                createAccountError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("An account with the same email is already in the Database")
                        .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
                        .show()
                }
            }
        })


        login_button.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(), password_edit.text.toString())
        }

        create_account_button.setOnClickListener{
            mainViewModel.onClickedCreateAccount(login_edit.text.toString().trim(), password_edit.text.toString())
        }
    }
}