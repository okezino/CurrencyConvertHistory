package com.example.currencyconverter.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.currencyconverter.R
import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.common.UiHelper
import com.example.currencyconverter.common.UiHelper.showSnackBar
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.presentation.model.ConversionRate
import com.example.currencyconverter.presentation.viewmodel.MainCurrencyViewModel


class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!
    private val mainCurrencyViewModel : MainCurrencyViewModel by activityViewModels()
    val listOfCurrency = listOf(  "AED" ,
        "AFN",
        "ALL",
        "AMD",
        "EGP",
        "EUR",
        "JPY",
        "USD")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConverterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainCurrencyViewModel.getCurrencySymbols()
        observeGetSymbols()
    }


    private fun observeGetSymbols(){

        mainCurrencyViewModel.currencySymbols.observe(viewLifecycleOwner){ resource ->

            when(resource){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.progressBar.showSnackBar("${resource.messages}")
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    val response = resource.data?.symbols
                    println(response)
                    val arrayAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.select_dialog_item_material, response!!)
                    binding.fromCurrencyDropdown.setAdapter(arrayAdapter)
                    binding.toCurrencyDropDown.setAdapter(arrayAdapter)
                }
            }

        }
    }

    fun activateTextWatcher(){
        binding.fromCurrencyInput.addTextChangedListener(getCurrencyValueChange)
    }

    val getCurrencyValueChange = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           val fromCurrency = binding.fromCurrencyDropdown.text.toString()
            val toCurrency = binding.toCurrencyDropDown.text.toString()
            if (fromCurrency.isNotBlank() && toCurrency.isNotBlank()){
                mainCurrencyViewModel.getCurrencyConverted(ConversionRate(p0.toString(),fromCurrency,toCurrency))
            }else {
            binding.fromCurrencyInput.showSnackBar("Pick Currency")
            }

        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }



}