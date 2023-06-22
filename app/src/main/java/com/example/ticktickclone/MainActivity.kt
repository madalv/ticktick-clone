package com.example.ticktickclone

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.ticktickclone.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private var showDetails = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "onCreate called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            // todo: open side menu
            Log.i(TAG, "Nav icon clicked")
        }

        binding.addTaskFloatingBtn.setOnClickListener {
            //todo: implement add task popup
            Log.i(TAG, "Add task btn clicked")
        }

        navController = Navigation.findNavController(this, R.id.main_activity_navhost_fragment)
        setupWithNavController(binding.bottomNavMenu, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        menu?.findItem(R.id.action_toggle_details)?.title =
            if (showDetails) getString(R.string.show_details)
            else getString(R.string.hide_details)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_sort -> {
                // todo: sort popup
                Log.i(TAG, "Sort action clicked")
                return true
            }

            R.id.action_toggle_details -> {
                showDetails = !showDetails

                item.title =
                    if (showDetails) getString(R.string.show_details)
                    else getString(R.string.hide_details)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

