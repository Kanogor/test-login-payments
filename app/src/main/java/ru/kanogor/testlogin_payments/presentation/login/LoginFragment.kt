package ru.kanogor.testlogin_payments.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kanogor.testlogin_payments.R
import ru.kanogor.testlogin_payments.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.login_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isTokenAdded.onEach {
            if (it) {
                findNavController().popBackStack(R.id.login, true)
                findNavController().navigate(R.id.action_login_to_payments)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.enterButton.setOnClickListener {
            isLoading(true)
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.postLoginPassword(login = login, password = password)

            viewModel.isDataLoading.onEach { isDataLoading ->
                if (!isDataLoading) {
                    isLoading(false)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
                if (isSuccess != null) if (isSuccess) {
                    if (findNavController().currentDestination?.label == "Login") {
                        findNavController().popBackStack(R.id.login, true)
                        findNavController().navigate(R.id.action_login_to_payments)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.incorrect_login_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressCircular.visibility = View.VISIBLE
            binding.loginInputLayout.isEnabled = false
            binding.passwordInputLayout.isEnabled = false
            binding.enterButton.isEnabled = false
        } else {
            binding.progressCircular.visibility = View.GONE
            binding.loginInputLayout.isEnabled = true
            binding.passwordInputLayout.isEnabled = true
            binding.enterButton.isEnabled = true
        }
    }

}