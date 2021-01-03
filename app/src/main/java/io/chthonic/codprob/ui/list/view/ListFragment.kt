package io.chthonic.codprob.ui.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.chthonic.codprob.R
import io.chthonic.codprob.databinding.ListFragmentBinding
import io.chthonic.codprob.ui.common.model.ChallengeUiModel
import io.chthonic.codprob.ui.common.view.BaseFragment
import io.chthonic.codprob.ui.common.view.LinearItemDecorator
import io.chthonic.codprob.ui.list.presentation.ListViewModelImpl
import io.chthonic.codprob.ui.list.view.adapter.ChallengeListAdapter

@AndroidEntryPoint
class ListFragment : BaseFragment() {

    // lib currently unable to inject viewModel to interface
    override val viewModel: ListViewModelImpl by viewModels()

    private var _binding: ListFragmentBinding? = null
    private val binding: ListFragmentBinding
        get() = requireNotNull(_binding)

    override val coordLayout
        get() = binding.coordList

    private val listView: RecyclerView
        get() = binding.listChallenge

    private val adapter: ChallengeListAdapter by lazy {
        ChallengeListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()

        viewModel.challengesObservable.observe(viewLifecycleOwner) {
            showChallenges(it)
        }
    }

    private fun setupList() {
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.addItemDecoration(LinearItemDecorator(resources.getDimensionPixelSize(R.dimen.grid_decoration_dimen)))

        adapter.onClickListener = { challenge ->
            navigateToChallenge(challenge)
        }
    }

    private fun navigateToChallenge(challenge: ChallengeUiModel) {
        val action = ListFragmentDirections.viewChallenge(
            challenge = challenge,
            title = challenge.name
        )
        findNavController().navigate(action)
    }

    private fun showChallenges(challenges: List<ChallengeUiModel>) {
        adapter.challenges = challenges
        binding.progress.visibility = View.GONE
        binding.groupEmpty.visibility = if (challenges.isEmpty()) View.VISIBLE else View.GONE
    }

}
