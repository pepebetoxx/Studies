package ademar.study.test.view.home

import ademar.study.test.R
import ademar.study.test.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        prepareTaskDescription()
    }

    companion object {

        fun populateIntent(intent: Intent, context: Context): Intent {
            intent.setClassName(context, HomeActivity::class.java.name)
            return intent
        }

    }

}
