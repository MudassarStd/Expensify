package com.std.composeexpensetracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.std.composeexpensetracker.ui.theme.Zinc

@Composable
fun TopRowHeader(
    modifier: Modifier = Modifier,
    title: String,
    showTrailingIcon: Boolean,
    trailingIcon: ImageVector = Icons.Default.Menu,
    onLeadClick: () -> Unit = {},
    onTrailClick: () -> Unit = {}
    ) {
    Row(
        modifier = modifier
            .padding(top = 24.dp).background(color = Zinc), verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onLeadClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        if (showTrailingIcon) {
            IconButton(onClick = onTrailClick) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}