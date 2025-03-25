package ir.ahe.abbas.composeperformance


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.ahe.abbas.library.litecolumn.LiteColumn
import ir.ahe.abbas.library.literow.LiteRow
import ir.ahe.abbas.library.literow.LiteRowScope
import ir.ahe.abbas.library.literow.VerticalAlignment


@Composable
fun LiteCustomListItem(
    item: ListItemModel,
    modifier: Modifier = Modifier
) {
    ItemContainer(
        modifier = modifier,
    ) {

        Box {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = androidx.compose.ui.Modifier.size(56.dp)
            )

            Icon(
                modifier = androidx.compose.ui.Modifier
                    .size(16.dp)
                    .align(androidx.compose.ui.Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.baseline_bookmark_24),
                tint = androidx.compose.ui.graphics.Color.Unspecified,
                contentDescription = null
            )

        }

        LiteColumn(spaceBetweenItem = 8.dp) {
            TitleAndTimeContainer(
                item.title,
                item.sendTime,
            )

            SubtitleContainer(uiModel = item)

        }
    }
}

@Composable
private fun ItemContainer(
    modifier: Modifier = Modifier,
    content: @Composable LiteRowScope.() -> Unit
) {
    LiteRow(
        modifier = modifier
            .height(IntrinsicSize.Max),
        spaceBetweenItem = 4.dp,
        verticalAlignment = VerticalAlignment.Center,
        content = content
    )
}

@Composable
private fun TitleAndTimeContainer(
    title: String,
    sentTime: String
) {

    LiteRow(
        verticalAlignment = VerticalAlignment.Center,
    ) {

        Text(
            text = title,
            modifier = Modifier.weight(1f)
        )
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.baseline_bookmark_24),
            tint = Color.Unspecified,
            contentDescription = null
        )


        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.baseline_check_24),
            contentDescription = null
        )

        Text(
            modifier = Modifier.sizeIn(minHeight = 16.dp, maxHeight = 22.dp),
            text = sentTime,
        )
    }
}

@Composable
private fun SubtitleContainer(
    uiModel: ListItemModel
) {
    LiteRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = VerticalAlignment.Center,
        spaceBetweenItem = 4.dp
    ) {

        Row(modifier = Modifier.weight(1f)) {

            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.baseline_egg_24),
                contentDescription = null
            )

            Text(text = uiModel.subtitle)
        }

        Spacer(modifier = Modifier.width(4.dp))

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(R.drawable.baseline_egg_24),
            contentDescription = null
        )

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(R.drawable.baseline_check_24),
            contentDescription = null
        )
    }
}
