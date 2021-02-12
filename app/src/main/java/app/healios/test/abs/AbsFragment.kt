package app.healios.test.abs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import app.healios.test.shared.Loggable
import dagger.android.support.DaggerFragment

abstract class AbsFragment : DaggerFragment(), Loggable {
    @LayoutRes
    protected abstract fun getLayout(): Int
    protected abstract fun onInflated(view: View)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayout(), container, false)
        onInflated(view)
        return view
    }
}