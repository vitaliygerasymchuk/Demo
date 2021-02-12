package app.healios.test.abs

import android.content.Context
import dagger.android.support.AndroidSupportInjection

abstract class AbsDaggerFragment : AbsFragment() {
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}