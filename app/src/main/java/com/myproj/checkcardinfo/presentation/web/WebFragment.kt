package com.myproj.checkcardinfo.presentation.web

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.myproj.checkcardinfo.R
import com.myproj.checkcardinfo.databinding.FragmentWebBinding
import com.myproj.checkcardinfo.util.Constant

class WebFragment: Fragment(R.layout.fragment_web) {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urlBank = arguments?.getString(Constant.URL_BANK)
        Log.d("MenuViewModel", "uri = $urlBank")
        binding.webView.apply {
            if (urlBank != null) {
                loadUrl(urlBank)
            }
            webViewClient = WebViewClient()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}