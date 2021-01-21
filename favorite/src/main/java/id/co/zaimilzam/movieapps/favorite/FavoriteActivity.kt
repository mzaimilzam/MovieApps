package id.co.zaimilzam.movieapps.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import id.co.zaimilzam.movieapps.favorite.databinding.ActivityFavoriteBinding
import id.co.zaimilzam.movieapps.favorite.di.favoriteModul
import org.koin.core.context.loadKoinModules


class FavoriteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModul)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navView.setupWithNavController(navController)

    }


}