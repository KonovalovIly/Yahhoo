package com.zeroninedev.manga.presentation.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroninedev.manga.di.DaggerFeatureMangaComponent
import com.zeroninedev.manga.presentation.category.screen.CategoryMangaScreen
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaViewModel
import com.zeroninedev.manga.presentation.detail.screen.DetailMangaScreen
import com.zeroninedev.manga.presentation.main.screen.MainMangaScreen
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaChapterScreen
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaViewModel
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen

/**
 * Main manga navigation destination grph
 *
 * @param navigator main navigator
 */
@ExperimentalComposeUiApi
@ExperimentalComposeApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.mainMangaNavigation(navigator: Navigator, appContext: Context) {

    val component = DaggerFeatureMangaComponent.builder().context(appContext).build()

    composable(Screen.MainScreen.ROUTE) {
        MainMangaScreen(navigator, component)
    }

    composable("${Screen.MangaDetailScreen.ROUTE}/{mangaId}") {
        val detailViewModel: DetailMangaViewModel = viewModel(factory = component.provideDetailMangaFactory())
        val mangaId = remember { it.arguments?.getString("mangaId").orEmpty() }

        LaunchedEffect(key1 = mangaId) {
            detailViewModel.loadMangaDetails(mangaId)
        }


        DetailMangaScreen(navigator, detailViewModel)
    }

    composable("${Screen.MangaChapterScreen.ROUTE}/{mangaId}/{chapterId}") {
        val mangaChapterViewModel: MangaChapterViewModel = viewModel(factory = component.provideMangaChapterFactory())
        val mangaId = remember { it.arguments?.getString("mangaId").orEmpty() }
        val chapterId = remember { it.arguments?.getString("chapterId").orEmpty() }
        LaunchedEffect(key1 = mangaId, key2 = chapterId) {
            mangaChapterViewModel.loadMangaChapter(mangaId, chapterId)
        }

        MangaChapterScreen(navigator, mangaChapterViewModel)
    }

    composable("${Screen.CategoryScreen.ROUTE}/{categoryName}/{categoryId}") {
        val categoryViewModel: CategoryMangaViewModel = viewModel(factory = component.provideCategoryMangaFactory())
        val categoryName = remember { it.arguments?.getString("categoryName").orEmpty() }
        val categoryId = remember { it.arguments?.getString("categoryId").orEmpty() }
        LaunchedEffect(key1 = categoryName, key2 = categoryId) {
            categoryViewModel.loadCategory(categoryName = categoryName, categoryId = categoryId)
        }

        CategoryMangaScreen(navigator, categoryViewModel)
    }
}