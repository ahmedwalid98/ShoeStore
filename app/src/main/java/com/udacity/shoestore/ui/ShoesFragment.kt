package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.viewmodels.MyViewModel
import kotlinx.android.synthetic.main.item_shoe.view.*

class ShoesFragment : Fragment() {

    private val viewModel:MyViewModel by activityViewModels()
    private lateinit var binding: FragmentShoesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoes, container, false)
        binding.addShoe.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoesFragmentDirections.actionShoesFragmentToShoeListFragment())
        }

        viewModel.shoeList.observe(viewLifecycleOwner){shoes->
            binding.shoeList.removeAllViews()
            for (shoe in shoes){
                addView(shoe.name)
            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                when (menuItem.itemId) {
                    R.id.logout -> {
                        findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToLoginFragment())
                        return true
                    }

                }
                return false
            }
        },viewLifecycleOwner)

        return binding.root
    }

    private fun addView(name: String) {
        val view = layoutInflater.inflate(R.layout.item_shoe,null)
        view.textView.text = name
        binding.shoeList.addView(view)
    }


}