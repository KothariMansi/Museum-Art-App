package com.example.museumartapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.museumartapp.R
import com.example.museumartapp.domain.models.Record
import com.example.museumartapp.ui.theme.MuseumArtAppTheme

@Composable
fun ArtDetail(
    record: Record,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.md_theme_secondary),
            contentColor = colorResource(id = R.color.md_theme_onSecondary)
        )
    ) {

        Column(
            modifier = Modifier
        ) {
            Box(modifier = Modifier) {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(record.baseimageurl).build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Icon(
                    imageVector = if (!selected) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 340.dp, top = 8.dp)
                        .clickable {
                            onClick()
                        }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Technique")
                    record.date?.let { Text(text = it) }
                }
                record.technique?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ArtDetailPreview() {
    MuseumArtAppTheme {
        ArtDetail(
            record = Record(
                1,
                "",
                "1998-02-01",
                "Kodak Ektapan"
            ), false
        ) {}
    }
}