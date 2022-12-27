package com.myproj.checkcardinfo.presentation.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.myproj.checkcardinfo.R
import com.myproj.checkcardinfo.ui.CheckCardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private val menuViewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = findNavController()
                CheckCardTheme {
                    MenuScreen(
                        navController = navController,
                        menuViewModel = menuViewModel
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            menuViewModel.menuState.collect { menuState ->
                when (menuState) {
                    MenuState.Start -> {}
                    is MenuState.Error -> {
                        onShowError(menuState.errorResources)
                    }
                    MenuState.Request -> {
                        onRequest()
                    }
                    is MenuState.Success -> {
                        menuViewModel.getCardInfo(menuState.cardInfo)
                    }
                }
            }
        }
    }

    private fun onRequest() {
        lifecycleScope.launchWhenStarted {
            menuViewModel.enteredCardNumber.collect { cardNumber ->
                if (isValidCardNumber(cardNumber)) {
                    requestInfo(cardNumber!!.toInt())
                } else {
                    onShowError(R.string.valid_card_error)
                }
            }
        }
    }

    private fun onShowError(errorResources: Int) {
        menuViewModel.onErrorShow(errorResources)
    }

    private fun requestInfo(cardNumber: Int) {
        menuViewModel.loadCardInfo(cardNumber)
    }

    private fun isValidCardNumber(text: CharSequence?): Boolean {
        Log.d("MenuFragment", "text = $text")
        val regex = "[0-9]".toRegex()
        if (text.isNullOrBlank()) return false
        return regex.containsMatchIn(text) && text.length == 8
    }
}