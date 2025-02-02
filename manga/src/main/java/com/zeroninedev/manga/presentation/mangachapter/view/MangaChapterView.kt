package com.zeroninedev.manga.presentation.mangachapter.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeroninedev.core_compose.components.image.MangaPageView
import com.zeroninedev.core_compose.components.text.ChapterPagesTextView

/**
 * Reader manga chapter view
 *
 * @param chapterPage list of chapter pages
 * @param prevPart callback on load prev chapter
 * @param nextPart callback on load next chapter
 * @param onErrorAction callback when image was loaded with error
 */
@Composable
internal fun MangaChapterView(
    chapterPage: List<String>,
    prevPart: () -> Unit,
    nextPart: () -> Unit,
    afterHalfPart: () -> Unit,
    onErrorAction: (String?) -> Unit
) {
    var page by remember { mutableStateOf(0) }
    if (page > chapterPage.size/2) afterHalfPart()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MangaPageView(
            url = chapterPage[page],
            onErrorResult = { onErrorAction(it) },
            onSuccessResult = {}
        )
        Column {
            Row(modifier = Modifier.weight(1f)) {
                Spacer(
                    modifier =
                    Modifier
                        .width(50.dp)
                        .fillMaxHeight()
                        .clickable {
                            if (page != 0) page -= 1
                            else prevPart()
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(
                    modifier =
                    Modifier
                        .width(50.dp)
                        .fillMaxHeight()
                        .clickable {
                            if (page < chapterPage.lastIndex) page += 1
                            else nextPart()
                        }
                )
            }
            ChapterPagesTextView(text = "${page+1}/${chapterPage.size}")
        }
    }
}