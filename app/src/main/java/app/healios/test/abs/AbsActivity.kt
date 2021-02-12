package app.healios.test.abs

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import app.healios.test.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar.*

abstract class AbsActivity : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayout(): Int
    abstract fun onInflated()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initToolbar()
        onInflated()
    }

    private fun initToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appbarConfig = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appbarConfig)
    }
}