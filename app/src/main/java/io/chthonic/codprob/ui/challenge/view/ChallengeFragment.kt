package io.chthonic.codprob.ui.challenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.chthonic.codprob.R
import io.chthonic.codprob.databinding.ChallengeFragmentBinding
import io.chthonic.codprob.entity.challenge.ChallengeSampleResult
import io.chthonic.codprob.ui.challenge.presentation.ChallengeViewModelImpl
import io.chthonic.codprob.ui.challenge.view.adapter.SampleListAdapter
import io.chthonic.codprob.ui.common.model.ChallengeUiModel
import io.chthonic.codprob.ui.common.view.BaseFragment
import io.chthonic.codprob.ui.common.view.LinearItemDecorator


@AndroidEntryPoint
class ChallengeFragment : BaseFragment() {

    override val coordLayout: CoordinatorLayout
        get() = binding.containerDetail

    private val args: ChallengeFragmentArgs by navArgs()

    private val challenge: ChallengeUiModel
        get() = args.challenge

    private val listView: RecyclerView
        get() = binding.listSample

    private val adapter: SampleListAdapter by lazy {
        SampleListAdapter()
    }

    // lib currently unable to inject viewmodel to interface
    override val viewModel: ChallengeViewModelImpl by viewModels()

    private var _binding: ChallengeFragmentBinding? = null
    private val binding: ChallengeFragmentBinding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setChallengeIndex(challenge.index)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChallengeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()

        viewModel.resultsObservable.observe(viewLifecycleOwner) {
            showSamples(it)
        }
    }

    private fun setupList() {
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.addItemDecoration(LinearItemDecorator(resources.getDimensionPixelSize(R.dimen.grid_decoration_dimen)))
    }

    private fun showSamples(sampleResults: List<ChallengeSampleResult>) {
        adapter.results = sampleResults
    }

//    override fun onResume() {
//        super.onResume()
//        viewLifecycleOwner.lifecycleScope.launch {
//            val results = viewModel.solveSamplesAsync(challenge.index).await()
//            Timber.v("solveSamplesAsync results = $results")
//            adapter.results = results
//        }
//    }

}
