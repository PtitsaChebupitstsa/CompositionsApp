package com.example.compositionsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.navigation.fragment.findNavController
import com.example.compositionapp.domain.entity.GameResult
import com.example.compositionsapp.R
import com.example.compositionsapp.databinding.FragmentGameFinishedBinding
import java.lang.RuntimeException

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        endGameStats()
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArgs()

    }
    private fun endGameStats(){
        binding.emojiResult.setImageResource(getSmileResId())
        binding.tvRequiredAnswer.text = getString(R.string.required_number_of_correct,gameResult.gameSettings.minCountOfRightAnswers.toString())
        binding.tvScoreAnswer.text = getString(R.string.your_score,gameResult.countOfRightAnswers.toString())
        binding.tvRequiredPercentage.text = getString(R.string.required_percentage_of_correct_answers,gameResult.gameSettings.minPercentOfRightAnswers.toString())
        binding.tvScorePrecentage.text = getString(R.string.percentage_of_correct_answers,getPercentOfRightAnswer().toString())

    }
    private fun getPercentOfRightAnswer()= with(gameResult){
        if (countOfQuestions==0) 0 else ((countOfRightAnswers/countOfQuestions.toDouble())*100).toInt()
    }
private fun getSmileResId():Int {
    return if (gameResult.winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun retryGame() {
        findNavController().popBackStack()
    }
    private fun parsArgs() {
         requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
             gameResult = it
         }
    }

    companion object {
        const val KEY_GAME_RESULT = "game_result"
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }

    }

}
////////////////////////

