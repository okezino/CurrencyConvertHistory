package com.example.currencyconverter.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.currencyconverter.common.DateUtil
import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.common.UiHelper.showSnackBar
import com.example.currencyconverter.data.model.Days
import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.presentation.model.ConversionRate
import com.example.currencyconverter.presentation.model.HistoryInfo
import com.example.currencyconverter.presentation.viewmodel.MainCurrencyViewModel


class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    lateinit var rateInfo: ConversionRate
    private val mainCurrencyViewModel: MainCurrencyViewModel by activityViewModels()

    private var yes = ""
    private var twoDays = ""
    private var threedays = ""


    val args: HistoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rateInfo = args.rateInfo

        DateUtil.getDayForPastThreeDays { yesterday, twoDaysAgo, threeDaysAgo ->
            yes = yesterday
            twoDays = twoDaysAgo
            threedays = threeDaysAgo
            binding.aDayAgoDate.text = yesterday
            binding.twoDayAgoDate.text = twoDaysAgo
            binding.threeDayAgoDate.text = threeDaysAgo
            populateView()
            mainCurrencyViewModel.getCurrencyHistory(
                Days(yesterday, twoDaysAgo, threeDaysAgo),
                HistoryInfo("", rateInfo.from, rateInfo.to)
            )
        }

        observeHistory()
    }


    private fun observeHistory() {
        mainCurrencyViewModel.currencyHistory.observe(viewLifecycleOwner) { resource ->

            when (resource) {
                is Resource.Loading -> {
                    Log.d("HOUSE", "HOUSE9")
                    binding.loading.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    binding.loading.visibility = View.INVISIBLE
                    binding.aDayAgoDate.showSnackBar("${resource.messages}")
                }

                is Resource.Success -> {
                    binding.loading.visibility
                    val data = resource.data
                    data?.let {
                        it.forEach { res ->
                            when (res.date) {
                                yes -> {
                                    binding.aDayAgoFromCurrency.text = ""
                                    binding.aDayAgoToCurrency.text = ""

                                }

                                twoDays -> {
                                    binding.twoDayAgoFromCurrency.text = ""
                                    binding.twoDayAgoToCurrency.text = ""

                                }
                                threedays -> {
                                    binding.threeDayAgoFromCurrency.text = ""
                                    binding.threeDayAgoToCurrency.text = ""

                                }
                            }
                        }

                    }
                }

            }

        }

    }

    private fun populateView() {

        val symbolFrom = rateInfo.from
        val symbolsTo = rateInfo.to
        binding.apply {
            aDayAgoFromCurrency.text = symbolFrom
            twoDayAgoFromCurrency.text = symbolFrom
            threeDayAgoFromCurrency.text = symbolFrom
            aDayAgoToCurrency.text = symbolsTo
            twoDayAgoToCurrency.text = symbolsTo
            threeDayAgoToCurrency.text = symbolsTo
        }
    }
}