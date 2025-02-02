package com.zeroninedev.core_compose.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import coil.size.Size
import com.zeroninedev.core_compose.ui.theme.ExtraSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.YahhooShapes
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

/**
 * Image holder to show manga with title preview
 *
 * @param modifier entered modifier from other scope
 * @param title manga title name
 * @param imageUrl manga image url
 * @param imageDescription description of manga preview
 * @param onClick callback on image click
 */
@Composable
fun MangaPreviewImageWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    imageDescription: String,
    onClick: () -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = Builder(LocalContext.current)
            .data(imageUrl)
            .allowHardware(false)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )

    Box(
        modifier = modifier
            .height(ExtraSize)
            .padding(SmallSize)
            .clip(YahhooShapes.medium)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentDescription = imageDescription,
            contentScale = ContentScale.Crop,
            painter = painter
        )
        Text(
            text = title,
            style = yahhooTypography.subtitle1,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.background,
            maxLines = MAX_LINES_IN_TITLE,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.primaryVariant
                        )
                    )
                )
        )
    }
}

private const val MAX_LINES_IN_TITLE = 2