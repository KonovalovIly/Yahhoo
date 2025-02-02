package com.zeroninedev.core_compose.components.toolbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.zeroninedev.core_compose.R
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.ZeroSize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

/**
 * Toolbar with menu icon and title name.
 * Color equals background color
 *
 * @param modifier entered modifier from other scope
 * @param screenTitle name of screen
 * @param onMenuIconClick callback on menu click
 */
@Composable
fun TopToolbar(
    modifier: Modifier = Modifier,
    screenTitle: String,
    onMenuIconClick: () -> Unit
) {
    TopAppBar(
        elevation = ZeroSize,
        contentPadding = WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
            .asPaddingValues(),
        backgroundColor = Color.Transparent,
        modifier = modifier
    ) {
        IconButton(onClick = onMenuIconClick) {
            Icon(
                Icons.Filled.Menu,
                contentDescription = stringResource(id = R.string.menu_description),
                tint = MaterialTheme.colors.primaryVariant
            )
        }
        Text(
            text = screenTitle,
            style = yahhooTypography.h6,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(horizontal = MediumSize, vertical = TinySize),
            maxLines = MAX_LINES_IN_TOOLBAR
        )
    }
}

private const val MAX_LINES_IN_TOOLBAR = 1