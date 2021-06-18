package ar.edu.unlam.intermediamarvelchallenge.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.ui.main.MainActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        startFirebaseAuth()
    }

    private fun startFirebaseAuth() {
        btn_login_facebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    result?.let {
                        val token = it.accessToken
                        val credential = FacebookAuthProvider.getCredential(token.token)
                        FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    startMainActivity()
                                } else {
                                    showAlert()
                                }


                            }
                    }
                }

                override fun onCancel() {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.cancel), Toast.LENGTH_LONG
                    ).show()
                }

                override fun onError(error: FacebookException?) {
                    showAlert()
                }

            })
        }
        btn_login.setOnClickListener {
            if (email.editText!!.text.isNotEmpty() && password.editText!!.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        email.editText!!.text.toString(),
                        password.editText!!.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            startMainActivity()
                        } else {
                            showAlert()
                        }
                    }
            }


        }
        btn_register.setOnClickListener {
            if (email.editText!!.text.isNotEmpty() && password.editText!!.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        email.editText!!.text.toString(),
                        password.editText!!.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            startMainActivity()

                        } else {
                            showAlertRegisterPassword()
                        }
                    }
            }
        }


    }

    private fun showAlertRegisterPassword() {
        Toast.makeText(
            this@LoginActivity,
            getString(R.string.passwordSmall), Toast.LENGTH_LONG
        ).show()
        email.setBoxStrokeColor(ContextCompat.getColor(this, R.color.red))
        password.setBoxStrokeColor(ContextCompat.getColor(this, R.color.red))
    }

    private fun startMainActivity() {
        val intent: Intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
    private fun showAlert() {
        Toast.makeText(
            this@LoginActivity,
            getString(R.string.error), Toast.LENGTH_LONG
        ).show()
        email.setBoxStrokeColor(ContextCompat.getColor(this, R.color.red))
        password.setBoxStrokeColor(ContextCompat.getColor(this, R.color.red))
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }
}