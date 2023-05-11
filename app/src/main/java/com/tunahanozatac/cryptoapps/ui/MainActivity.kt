package com.tunahanozatac.cryptoapps.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.util.gone
import com.tunahanozatac.cryptoapps.util.visible
import com.tunahanozatac.cryptoapps.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
        setNavMenuVisibility()
    }

    private fun setUpNavigation() {
        val navController = navHostFragment().navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setNavMenuVisibility() {
        val navController = navHostFragment().navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment, R.id.loginFragment, R.id.registerFragment -> {
                    binding.bottomNavigationView.gone()
                }

                else -> {
                    binding.bottomNavigationView.visible()
                }
            }
        }
    }

    private fun navHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }
}