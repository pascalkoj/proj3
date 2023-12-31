package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MathScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class MathScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun ToEndScreen()
    {
        val fm = parentFragmentManager
        if (fm != null)
        {
            val startScreen = fm.findFragmentById(R.id.nav_host_fragment) as MathScreen
            val navController = startScreen.findNavController()
            navController.navigate(R.id.action_mathScreen_to_mathEndScreen)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_math_screen, container, false)
        val answerText = view.findViewById<TextInputEditText>(R.id.textInputEditText)
        val operand1Text = view.findViewById<TextView>(R.id.textOp1)
        val operand2Text = view.findViewById<TextView>(R.id.textOp2)
        val operationText = view.findViewById<TextView>(R.id.textOperation)
        val mathapp = MathApp.instance

        val firstOpPair = mathapp.operandList.last()
        operand1Text.setText(firstOpPair.first.toString())
        operand2Text.setText(firstOpPair.second.toString())

        var operationStr = ""
        if (mathapp.operationMode == OperationMode.ADDITION) operationStr = "+"
        if (mathapp.operationMode == OperationMode.MULTIPLICATION) operationStr = "*"
        if (mathapp.operationMode == OperationMode.DIVISION) operationStr = "/"
        if (mathapp.operationMode == OperationMode.SUBTRACTION) operationStr = "-"
        operationText.setText(operationStr)


        // end this screen
        val doneButton = view.findViewById<Button>(R.id.bDone)
        doneButton.setOnClickListener {
            if (mathapp.ProcessAnswer(answerText.text.toString()))
            {
                ToEndScreen()
            }
            else
            {
                val nextOperands = mathapp.operandList.last()
                operand1Text.setText(nextOperands.first.toString())
                operand2Text.setText(nextOperands.second.toString())
            }
            answerText.setText("")
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MathScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MathScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}