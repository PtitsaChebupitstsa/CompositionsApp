package com.example.compositionapp.domain.usecases

import com.example.compositionapp.domain.entity.GameSettings
import com.example.compositionapp.domain.entity.Level
import com.example.compositionapp.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }

}