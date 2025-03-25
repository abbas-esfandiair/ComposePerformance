package ir.ahe.abbas.composeperformance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun CustomListItem(
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
                modifier = Modifier.size(56.dp)
            )

            Icon(
                modifier = Modifier.size(16.dp).align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.baseline_bookmark_24),
                tint = Color.Unspecified,
                contentDescription = null
            )

        }


        Column {
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
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
private fun TitleAndTimeContainer(
    title: String,
    sentTime: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
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


        Spacer(modifier = Modifier.width(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {

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
}

@Composable
private fun SubtitleContainer(
    uiModel: ListItemModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
       Row (modifier = Modifier.weight(1f)) {

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
