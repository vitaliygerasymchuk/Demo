package app.healios.test.abs

import androidx.viewbinding.ViewBinding
import app.healios.test.shared.Loggable
import dagger.android.support.DaggerFragment

abstract class AbsFragment : DaggerFragment(), Loggable {

    protected var viewBinding: ViewBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}