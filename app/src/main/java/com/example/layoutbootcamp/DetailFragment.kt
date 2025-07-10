package com.example.layoutbootcamp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
// private const val ARG_PARAM1 = "param1"
// private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {
    // TODO: Rename and change types of parameters
    // private var param1: String? = null
    // private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val titleArg = args.title
        val dateArg = args.date

        val titleText = view.findViewById<TextView>(R.id.detailTitle)
        val dateText = view.findViewById<TextView>(R.id.detailDate)
        val contentText = view.findViewById<TextView>(R.id.detailContent)

        titleText.text = titleArg
        dateText.text = dateArg

        // Isi dummy konten berdasarkan judul
        val dummyContent = when {
            titleArg.contains("Endog", true) -> "- 2 butir telur ayam\n- 1 sdm kecap\n- Bakar hingga matang"
            titleArg.contains("Jurnal", true) -> "- Baca 3 paper\n- Catat highlight penting\n- Riset topik lanjutan"
            else -> "- Aktivitas belum memiliki detail spesifik."
        }
        contentText.text = dummyContent

        // Tombol kembali
        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        // Tombol hapus
        view.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            Toast.makeText(requireContext(), "Data dihapus (simulasi)", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        // Tombol ubah
        view.findViewById<Button>(R.id.editButton).setOnClickListener {
            Toast.makeText(requireContext(), "Fitur ubah belum tersedia", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        /*
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            } */
    }
}