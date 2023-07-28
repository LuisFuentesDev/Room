package com.example.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.room.databinding.FragmentAgregarFragmentoBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarFragmento.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarFragmento : Fragment() {

    lateinit var binding: FragmentAgregarFragmentoBinding
    lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgregarFragmentoBinding.inflate(layoutInflater, container, false)
        initReposotorio()
        initListener()
        return binding.root
    }

    private fun initReposotorio() {
        repositorio = Repositorio(TareaBaseDatos.getDatabase(requireContext()).getTaskDao())
    }
    private fun initListener() {
        binding.buttonAgregar.setOnClickListener {

            val texto = binding.editTextAgregar.text.toString()
            Toast.makeText(requireContext(), "Tarea Agregada", Toast.LENGTH_SHORT).show()
            guardarTarea(texto)
            cargarTarea()
        }
    }

    private fun guardarTarea(texto: String) {

        val tarea = Tarea(texto)
        GlobalScope.launch { repositorio.intertTask(tarea) }

    }
    private fun cargarTarea() {

        val tareas = repositorio.getTareas().observe(requireActivity()) {
            val tareaAsText = it.joinToString("\n") { it.nombre }
            binding.textViewMostrar.text = tareaAsText
        }

    }
}