package com.pride.roomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.pride.roomtest.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private lateinit var binding : FragmentBlankBinding
    private val viewM : ViewM by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            writeData()
            Navigation.findNavController(binding.root).navigate(R.id.action_blankFragment_to_listFragment)
        }
        viewM.message.observe(viewLifecycleOwner){
            if(it!=null) Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun writeData() {
        val text = binding.editTextTextPersonName.text.toString()
        if (text.isNotEmpty()) {
            val name = Name(null,text)
            viewM.writeToDb(name)
        }
    }
}