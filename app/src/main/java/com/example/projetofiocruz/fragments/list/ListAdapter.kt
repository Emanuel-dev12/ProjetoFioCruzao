package com.example.projetofiocruz.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofiocruz.model.Paciente
import com.example.projetofiocruz.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var pacienteLista = emptyList<Paciente>()

    // ViewHolder usando ViewBinding para acessar os elementos do layout
    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflar o layout usando ViewBinding
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pacienteLista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = pacienteLista[position]

        // Associar os dados aos TextViews do layout
        holder.binding.apply {
            idTxt.text = currentItem.id.toString()
            nomeTxt.text = currentItem.nome
            localTxt.text = currentItem.local
            idadeTxt.text = currentItem.idade.toString()

            rowLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    // Método para atualizar a lista e notificar o adaptador
    fun setData(paciente: List<Paciente>) {
        this.pacienteLista = paciente
        notifyDataSetChanged()
    }
}