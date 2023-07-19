package com.ainarm.database_with_phlip.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ainarm.database_with_phlip.SortType
import com.ainarm.database_with_phlip.state.ContactEvent
import com.ainarm.database_with_phlip.state.ContactState

@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
        },
    ) { PaddingValues ->

        if(state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortType.values().forEach { sortType ->

                        Box(
                            contentAlignment = CenterStart
                            ,modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFBC02D))

                        ){

                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(ContactEvent.SortByName(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {

                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(ContactEvent.SortByName(sortType))
                                }
                            )
                            Text(text = sortType.name, modifier = Modifier.padding(end = 4.dp))
                        }
                        }
                    }
                }
            }

            items(state.contacts) { contact ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth().clickable {

                          onEvent(ContactEvent.UpdateContact(contact))
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .height(80.dp)
                , backgroundColor = Color(0xFF0288D1)


                ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "${contact.firstName} ${contact.lastName}",
                            fontSize = 20.sp, textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = contact.phoneNumber, fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(start = 8.dp,bottom = 4.dp),
                                color = Color.White)
                    }
                    IconButton(onClick = {
                        onEvent(ContactEvent.DeleteContact(contact))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact"
                        )
                    }
                }
                }
            }
        }
    }
}