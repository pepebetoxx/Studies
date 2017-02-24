package ademar.study.template.view.detail

import ademar.study.template.R
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.presenter.detail.DetailPresenter
import ademar.study.template.presenter.detail.DetailView
import ademar.study.template.view.base.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_fragment.*
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailView {

    @Inject lateinit var presenter: DetailPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component.inject(this)
        presenter.onAttachView(this)

        reload.setOnClickListener { presenter.onReloadClick() }
        if (!context.resources.getBoolean(R.bool.large_screen)) {
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener { getBaseActivity()?.back() }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetachView()
    }

    override fun showLoading() {
        text.visibility = GONE
        load.visibility = VISIBLE
        reload.visibility = GONE
    }

    override fun showRetry() {
        text.visibility = GONE
        load.visibility = GONE
        reload.visibility = VISIBLE
    }

    override fun showContent() {
        text.visibility = VISIBLE
        load.visibility = GONE
        reload.visibility = GONE
    }

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        text.text = viewModel.message
    }

    companion object {

        fun newInstance(): DetailFragment {
            return DetailFragment()
        }

    }

}