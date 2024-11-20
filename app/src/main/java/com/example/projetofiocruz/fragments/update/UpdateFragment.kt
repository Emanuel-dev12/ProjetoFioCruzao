package com.example.projetofiocruz.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.projetofiocruz.R
import com.example.projetofiocruz.databinding.FragmentListBinding
import com.example.projetofiocruz.databinding.FragmentUpdateBinding
import com.example.projetofiocruz.model.Paciente
import com.example.projetofiocruz.viewmodel.PacienteViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mPacienteViewModel: PacienteViewModel

    var _binding: FragmentUpdateBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mPacienteViewModel = ViewModelProvider(this).get(PacienteViewModel::class.java)

        binding.updateNome.setText(args.currentPaciente.nome)
        binding.updateLocal.setText(args.currentPaciente.local)
        binding.updateIdade.setText(args.currentPaciente.idade.toString())

        binding.updatebutton.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val nome = binding.updateNome.text.toString()
        val local = binding.updateLocal.text.toString()
        val idade = Integer.parseInt(binding.updateIdade.text.toString())

        if (inputCheck(nome, local, binding.updateIdade.text)) {

            val updatedPaciente = Paciente(args.currentPaciente.id, nome, local, idade)

            mPacienteViewModel.updatePaciente(updatedPaciente)

            Toast.makeText(requireContext(), "Atualizado!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        else{
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nome: String, local: String, idade: Editable): Boolean {
        return !(TextUtils.isEmpty(nome) && TextUtils.isEmpty(local) && idade.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deletePaciente()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deletePaciente() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){_, _ ->
            mPacienteViewModel.deletePaciente(args.currentPaciente)
            Toast.makeText(requireContext(), "${args.currentPaciente.nome} Foi removido(a) com sucesso!", Toast.LENGTH_SHORT).show()
            //Retorna ao menu principal:
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Não"){_, _ ->}
        builder.setTitle("Exluir ${args.currentPaciente.nome}?")
        builder.setMessage("Você tem certeza?")
        builder.create().show()
    }

}