package com.myproj.checkcardinfo.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.myproj.checkcardinfo.presentation.menu.MenuViewModel
import com.myproj.checkcardinfo.ui.CheckCardTheme

class BaseFragment : Fragment() {

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
                    BaseScreen(
                        navController = navController,
                        menuViewModel = menuViewModel
                    )
                }
            }
        }
    }
}