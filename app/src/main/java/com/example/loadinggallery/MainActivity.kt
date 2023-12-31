package com.example.loadinggallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.loadinggallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)
        navController.addOnDestinationChangedListener{
            _, destination : NavDestination, _ ->
            setTitleFromDestination(destination)
        }

    }

        private fun setTitleFromDestination(destination: NavDestination) {
            when (destination.id) {
                R.id.imagesFragment -> supportActionBar?.title = "Images"
                R.id.videosFragment -> supportActionBar?.title = "Videos"
            }
        }

}
