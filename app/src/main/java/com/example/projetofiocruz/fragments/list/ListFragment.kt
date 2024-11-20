package com.example.projetofiocruz.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetofiocruz.R
import com.example.projetofiocruz.viewmodel.PacienteViewModel
import com.example.projetofiocruz.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var mPacienteViewModel: PacienteViewModel

    var _binding: FragmentListBinding? =null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = ListAdapter()
        val recyclerView = binding.recycleview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mPacienteViewModel = ViewModelProvider(this).get(PacienteViewModel::class.java)
        mPacienteViewModel.readAllData.observe(viewLifecycleOwner, Observer { paciente ->
            adapter.setData(paciente)
        })


        binding.floatingActionButton.setOnClickListener {
        findNavController().navigate(R.id.action_listFragment_to_addFragment)
    }

        setHasOptionsMenu(true)

        return binding.root

    }

    //private fun insertDataToDataBase() {

    //}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllPacientes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllPacientes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){_, _ ->
            mPacienteViewModel.deleteAllPaciente()
            Toast.makeText(requireContext(), "Todos os dados foram removidos com sucesso.", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Não"){_, _ ->}
        builder.setTitle("Exluir todos os dados?")
        builder.setMessage("Você tem certeza?")
        builder.create().show()
    }
}