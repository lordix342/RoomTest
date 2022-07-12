package com.pride.roomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pride.roomtest.databinding.FragmentListBinding

class ListFragment : Fragment() {
   private lateinit var binding: FragmentListBinding
    private val rcAdapter = RcAdapter()
    private val viewM : ViewM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewM.readDb()
        viewM.list.observe(viewLifecycleOwner) {
            if(it!=null) {
                binding.rcView.adapter = rcAdapter
                binding.rcView.layoutManager = LinearLayoutManager(requireContext())
                rcAdapter.setData(it)
            }
        }
    }
}