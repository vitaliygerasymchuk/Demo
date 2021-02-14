package app.healios.test.ui

import android.view.View
import app.healios.test.ui.abs.AbsActivity
import app.healios.test.databinding.ActivityMainBinding

class MainActivity : AbsActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun getLayout(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }
}