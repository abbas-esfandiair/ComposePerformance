package ir.ahe.abbas.composeperformance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val data = generateItemUiData().collectAsLazyPagingItems()

            Column {
                LazyColumn(
                    Modifier.semantics {
                        testTagsAsResourceId = true
                        testTag = "list_id"
                    }) {
                    items(count = data.itemCount, key = data.itemKey { it.id }) {
                        data[it]?.let { item -> CustomListItem(item) }
                    }
                }
            }

        }
    }
}

private fun generateItemUiData(count: Int = 320): Flow<PagingData<ListItemModel>> {
    val list = mutableListOf<ListItemModel>()
    repeat(count) {
        val item = ListItemModel(
            id = it,
            title = "title $it",
            subtitle = "subtitle test $it",
            sendTime = "12:10"
        )
        list.add(item)
    }

    return flowOf(PagingData.from(list))
}
