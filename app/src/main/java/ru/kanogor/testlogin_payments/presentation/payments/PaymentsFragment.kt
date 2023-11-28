package ru.kanogor.testlogin_payments.presentation.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kanogor.testlogin_payments.R
import ru.kanogor.testlogin_payments.databinding.FragmentPaymentsBinding

class PaymentsFragment : Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getInfo()

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_payments_to_login)
        }
    }
}