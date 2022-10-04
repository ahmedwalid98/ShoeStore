package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewmodels.MyViewModel


class ShoeListFragment : Fragment() {
    private val viewModel:MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentShoeListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list,container,false)
        viewModel.resetNewShoe()
        binding.viewModel = viewModel

        binding.saveButton.setOnClickListener{view ->
            if (viewModel.addNewShoe()){
                view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoesFragment())
            }else{
                Snackbar.make(
                    requireView(),
                    "Incomplete text",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        return binding.root
    }


}