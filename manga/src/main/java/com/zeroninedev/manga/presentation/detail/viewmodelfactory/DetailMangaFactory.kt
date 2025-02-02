package com.zeroninedev.manga.presentation.detail.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeroninedev.manga.domain.usecase.GetDetailMangaUseCase
import com.zeroninedev.manga.domain.usecase.SaveChaptersUseCase
import com.zeroninedev.manga.domain.usecase.UpdateChapterInfoUseCase
import com.zeroninedev.manga.domain.usecase.UpdateMangaInfoUseCase
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaViewModel
import javax.inject.Inject

internal class DetailMangaFactory @Inject constructor(
    private val getDetailMangaUseCase: GetDetailMangaUseCase,
    private val updateMangaUseCase: UpdateMangaInfoUseCase,
    private val updateChapterInfoUseCase: UpdateChapterInfoUseCase,
    private val saveChaptersUseCase: SaveChaptersUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailMangaViewModel(
            getDetailMangaUseCase = getDetailMangaUseCase,
            updateMangaUseCase = updateMangaUseCase,
            updateChapterInfoUseCase = updateChapterInfoUseCase,
            saveChaptersUseCase = saveChaptersUseCase
        ) as T
}