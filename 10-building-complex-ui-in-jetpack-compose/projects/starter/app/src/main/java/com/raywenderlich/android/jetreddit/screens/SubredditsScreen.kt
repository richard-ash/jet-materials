/*
 * Copyright (c) 2021 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 * 
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.android.jetreddit.screens

import android.text.Layout
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.raywenderlich.android.jetreddit.R
import com.raywenderlich.android.jetreddit.components.BackgroundText
import com.raywenderlich.android.jetreddit.models.SubredditModel

val subreddits = listOf(
    SubredditModel(
        R.string.raywenderlich,
        R.string.members_120k,
        R.string.welcome_to_raywenderlich
    ),
    SubredditModel(
        R.string.programming,
        R.string.members_600k,
        R.string.hello_programmers
    ),
    SubredditModel(
        R.string.android,
        R.string.members_400k,
        R.string.welcome_to_android
    ),
    SubredditModel(
        R.string.androiddev,
        R.string.members_500k,
        R.string.hello_android_devs
    )
)

val mainCommunities = listOf(R.string.all, R.string.public_network)

val communities = listOf(
    R.string.digitalnomad,
    R.string.covid19,
    R.string.memes,
    R.string.humor,
    R.string.worldnews,
    R.string.dogs,
    R.string.cats
)

@Composable
fun SubredditsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(text = stringResource(id = R.string.recently_visited_subreddits), fontSize = 12.sp, style = MaterialTheme.typography.subtitle1, modifier = modifier.padding(16.dp))

        LazyRow(modifier = modifier.padding(end = 16.dp)) {
            items(subreddits) {
                Subreddit(subredditModel = it)
            }
        }

        Communities(modifier = modifier)
    }
}

@Composable
fun Subreddit(subredditModel: SubredditModel, modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .size(120.dp)
            .padding(horizontal = 2.dp, vertical = 4.dp)
    ) {
        SubredditBody(subredditModel = subredditModel)
    }
}

@Composable
fun SubredditBody(subredditModel: SubredditModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        Box(modifier = modifier.padding(bottom = 10.dp)) {
            SubredditImage(modifier = modifier)
            SubredditIcon(
                modifier = modifier
                    .align(BottomCenter)
                    .offset(y = 10.dp)
            )
        }

        SubredditName(
            modifier = modifier.align(CenterHorizontally),
            nameStringRes = subredditModel.nameStringRes
        )

        SubredditMembers(
            modifier = modifier.align(CenterHorizontally),
            membersStringRes = subredditModel.membersStringRes
        )

        SubredditDescription(
            modifier = modifier.align(CenterHorizontally),
            descriptionStringRes = subredditModel.descriptionStringRes
        )
    }
}

@Composable
fun SubredditImage(modifier: Modifier) {
    Image(
        painter = ColorPainter(Color.Blue),
        contentDescription = stringResource(id = R.string.subreddit_image),
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
    )
}

@Composable
fun SubredditIcon(modifier: Modifier) {
    Icon(
        modifier = modifier,
        tint = Color.LightGray,
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_planet),
        contentDescription = stringResource(id = R.string.subreddit_icon),
    )
}

@Composable
fun SubredditName(modifier: Modifier, @StringRes nameStringRes: Int) {
    Text(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        text = stringResource(nameStringRes),
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun SubredditMembers(modifier: Modifier, @StringRes membersStringRes: Int) {
    Text(
        fontSize = 8.sp,
        text = stringResource(membersStringRes),
        color = Color.Gray,
        modifier = modifier
    )
}

@Composable
fun SubredditDescription(modifier: Modifier, @StringRes descriptionStringRes: Int) {
    Text(
        fontSize = 8.sp,
        text = stringResource(descriptionStringRes),
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun Community(text: String, modifier: Modifier = Modifier, onCommunityClicked: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .clickable { onCommunityClicked.invoke() }
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.subreddit_placeholder),
            contentDescription = stringResource(id = R.string.community_icon),
            modifier = modifier
                .size(24.dp)
                .clip(CircleShape)
        )
        Text(
            text = text,
            fontSize = 10.sp,
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(start = 16.dp)
                .align(CenterVertically)
        )
    }
}

@Composable
fun Communities(modifier: Modifier = Modifier) {
    mainCommunities.forEach {
        Community(text = stringResource(id = it))
    }

    Spacer(modifier = modifier.height(4.dp))

    BackgroundText(text = stringResource(id = R.string.communities))

    communities.forEach {
        Community(text = stringResource(id = it))
    }
}

@Preview
@Composable
fun SubredditBodyPreview() {
    SubredditBody(SubredditModel.DEFAULT_SUBREDDIT)
}

@Preview
@Composable
fun SubredditPreview() {
    Subreddit(SubredditModel.DEFAULT_SUBREDDIT)
}

@Preview
@Composable
fun CommunityPreview() {
    Community(stringResource(id = R.string.raywenderlich_com))
}

@Preview
@Composable
fun CommunitiesPreview() {
    Column {
        Communities()
    }
}
