package com.example.currencyconverter.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.currencyconverter.R
import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.common.UiHelper.showSnackBar
import com.example.currencyconverter.data.model.HistoryResponse
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.presentation.model.ConversionRate
import com.example.currencyconverter.presentation.model.HistoryInfo
import com.example.currencyconverter.presentation.viewmodel.MainCurrencyViewModel


class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    lateinit var rateInfo : ConversionRate
    private val mainCurrencyViewModel : MainCurrencyViewModel by activityViewModels()
    private var day = 1
    private val listHistory = ArrayList<HistoryResponse>()

    val args : HistoryFragmentArgs by navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        mainCurrencyViewModel.getCurrencyHistory(HistoryInfo("", rateInfo.from,  rateInfo.to))

    }



    fun observeHistory(){
        mainCurrencyViewModel.currencyHistory.observe(viewLifecycleOwner){
            resource ->

            when(resource){
                is Resource.Loading -> {
                }

                is Resource.Error -> {
                }

                is Resource.Success -> {
                    val response = resource.data
                    listHistory.add(response!!)
                    if(day < 4){
                        mainCurrencyViewModel.getCurrencyHistory(HistoryInfo("", rateInfo.from,  rateInfo.to ))
                    }else {
                        populateView()
                    day += 1



                }
            }
        }
    }
}

    private fun populateView() {

    }
}

