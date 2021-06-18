package ar.edu.unlam.intermediamarvelchallenge.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.ui.character.CharacterFragment
import ar.edu.unlam.intermediamarvelchallenge.ui.event.EventsFragment
import ar.edu.unlam.intermediamarvelchallenge.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var mainCharacterFrag = CharacterFragment()
    var mainEventFrag = EventsFragment()
    private val myOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            binding.bottomNavbar.itemIconTintList = null
            when (item.itemId) {
                R.id.Characters -> {
                    showSelectedScreen(mainCharacterFrag)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.Events -> {
                    showSelectedScreen(mainEventFrag)
                    return@OnNavigationItemSelectedListener true
                }

            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.container_fragment)
        binding.bottomNavbar.setupWithNavController(navController)
        binding.bottomNavbar.itemIconTintList = null
        binding.bottomNavbar.setOnNavigationItemSelectedListener(myOnNavigationItemSelectedListener)


    }

    private fun showSelectedScreen(frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container_fragment, frag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()


    }
}