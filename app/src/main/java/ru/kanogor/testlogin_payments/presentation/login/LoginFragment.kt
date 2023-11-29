package ru.kanogor.testlogin_payments.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enterButton.setOnClickListener {
            isLoading(true)
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            Log.d("LoginFGMT", "login = $login, password = $password")
            viewModel.postLoginPassword(login = login, password = password)
            viewModel.isDataLoading.onEach { isDataLoading ->
                Log.d("LoginFGMT", "isDataLoading = $isDataLoading")
                if (!isDataLoading) {
                    isLoading(false)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
            viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
                Log.d("LoginFGMT", "isSuccess = $isSuccess")
                if (isSuccess != null) if (isSuccess) {
                    Log.d("LoginFGMT", "It's OK")
                    findNavController().navigate(R.id.payments)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Неправильный логин и пароль",
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