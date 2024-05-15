package com.naszi.mobilapp.to_dolistapp.ui

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.naszi.mobilapp.to_dolistapp.R

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    val navigationIcon: (@Composable () -> Unit)? =
        if (!title.contains("Task List")) {
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = colorResource(id = R.color.teal_200),
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.teal_200),
                modifier = Modifier.run {
                    padding(start = 4.dp).heightIn(max = 24.dp)
                }
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.teal_700),
        navigationIcon = navigationIcon
    )
}