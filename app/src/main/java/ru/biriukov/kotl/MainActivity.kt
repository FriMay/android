package ru.biriukov.kotl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.apollographqlandroid.LoginUserQuery
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val apolloClient = ApolloClient.builder().serverUrl("http://127.0.0.1:5000").build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun login(view: View) {
        val login = login.toString()
        val password = password.toString()

        if (login.trim().isEmpty()) {
            alert(R.string.login_error)
            return
        }

        if (password.trim().isEmpty()) {
            alert(R.string.password_error)
            return
        }

        apolloClient.query(LoginUserQuery.builder().login(login).password(password).build())
            .enqueue(object : ApolloCall.Callback<LoginUserQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    alert(R.string.wrong_login_or_password)
                }

                override fun onResponse(response: Response<LoginUserQuery.Data>) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun alert(text: Int) {
        val toast = Toast.makeText(applicationContext, getString(text), Toast.LENGTH_SHORT);
        toast.show()
    }
}
