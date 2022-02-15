package com.autumnsun.suncoinmarket.features.feature_auth.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.presentation.MainActivity
import com.autumnsun.suncoinmarket.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkUser()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.auth_fragment_nav_graph) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    private fun checkUser() {
        //splash screen token check!!
        /*       lifecycleScope.launch {
                   var token = ""
                   val sharedToken = sharedPreferences.getString("token", null)
                   firebaseAuth.getAccessToken(true).addOnSuccessListener { getTokenResult ->
                       getTokenResult.token?.let {
                           token = it
                       }
                   }.await()
               }
               firebaseAuth.a
               if (sharedToken != null && sharedToken.isNotBlank()) {
                   if (sharedToken != token) {
                       Snackbar.make(
                           binding.root,
                           getString(R.string.login_expired),
                           Snackbar.LENGTH_LONG
                       )
                           .setAnimationMode(Snackbar.ANIMATION_MODE_FADE).show()
                       return
                   }
               }*/
        if (firebaseAuth.currentUser != null) {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.auth_fragment_nav_graph)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}