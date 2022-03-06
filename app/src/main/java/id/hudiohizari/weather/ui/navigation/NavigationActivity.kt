package id.hudiohizari.weather.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import id.hudiohizari.weather.R
import id.hudiohizari.weather.databinding.ActivityNavigationBinding
import id.hudiohizari.weather.util.extention.toast

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    private var twice = false

    override fun onBackPressed() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            if (twice) {
                finish()
                return
            }
            twice = true
            toast(getString(R.string.pressOnceMore))
            Handler(Looper.getMainLooper()).postDelayed({ twice = false }, 2000)
        } else super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initView()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        binding.lifecycleOwner = this
    }

    private fun initView() {
        // Setup navController
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fcvMain
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        binding.bnvMain.setupWithNavController(navController)

        // Setup the ActionBar with navController and 2 top level destinations
        setSupportActionBar(binding.tMain)
        supportActionBar?.hide()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeNavigation, R.id.favoriteNavigation -> supportActionBar?.hide()
                else -> supportActionBar?.show()
            }
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeNavigation,
                R.id.favoriteNavigation
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}