package com.engamsba.mylife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.engamsba.mylife.views.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//         val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

         navController  = findNavController(R.id.nav_host_fragment)
         setupBottomNavMenu(navController)

//         bottomNavigationView.setupWithNavController(navController)

         val appBarConfiguration = AppBarConfiguration(setOf(R.id.noteListFragment, R.id.taskCategoriesFragment))

//         setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav?.setupWithNavController(navController)
    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()
//    }

}
