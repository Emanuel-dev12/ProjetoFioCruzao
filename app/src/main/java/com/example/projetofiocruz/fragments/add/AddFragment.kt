package com.example.projetofiocruz.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetofiocruz.R
import com.example.projetofiocruz.model.Paciente
import com.example.projetofiocruz.viewmodel.PacienteViewModel
import com.example.projetofiocruz.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var mPacienteViewModel: PacienteViewModel

    var _binding: FragmentAddBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mPacienteViewModel = ViewModelProvider(this).get(PacienteViewModel::class.java)

        binding.addbutton.setOnClickListener {
            insertDataToDataBase()
        }

        return binding.root

    }

    private fun insertDataToDataBase() {
        val nome = binding.addNome.text.toString()
        val local = binding.addLocal.text.toString()
        val idade = binding.addIdade.text
        val cpf = binding.addCpf.text.toString()
        val numero = binding.addNum.text.toString()

        if (inputCheck(nome, local, idade, cpf, numero)){
            // Criar Paciente
            val paciente = Paciente(0, nome, local, Integer.parseInt(idade.toString()), cpf, numero)
            // Adicionar ao banco de dados
            mPacienteViewModel.addPaciente(paciente)
            Toast.makeText( requireContext(),"Adicionado!", Toast.LENGTH_SHORT).show()
            // Voltar
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText( requireContext(),"Restam campos para preencher", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(nome: String, local: String, idade: Editable, cpf: String, telefone:String): Boolean {
            return !(TextUtils.isEmpty(nome) && TextUtils.isEmpty(local) && idade.isEmpty())
    }

}