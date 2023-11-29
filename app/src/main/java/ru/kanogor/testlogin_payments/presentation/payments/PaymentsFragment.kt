package ru.kanogor.testlogin_payments.presentation.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kanogor.testlogin_payments.R
import ru.kanogor.testlogin_payments.databinding.FragmentPaymentsBinding
import ru.kanogor.testlogin_payments.presentation.adapters.PaymentItemsAdapter

class PaymentsFragment : Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentsViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.payment_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exitButton.setOnClickListener {
            viewModel.exitAccount()
            findNavController().popBackStack(R.id.payments, true)
            findNavController().navigate(R.id.login)
        }

        val paymentAdapter = PaymentItemsAdapter()

        binding.paymentsRecyclerView.adapter = paymentAdapter
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        viewModel.isLoading.onEach {
            binding.swipeRefresh.isRefreshing = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.responseInfo.onEach {
            paymentAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}