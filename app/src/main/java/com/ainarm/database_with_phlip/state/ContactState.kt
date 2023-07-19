package com.ainarm.database_with_phlip.state

import com.ainarm.database_with_phlip.SortType
import com.ainarm.database_with_phlip.model.Contact

data class ContactState (
    val contacts:List<Contact> = emptyList(),
    val firstName:String="",
    val lastName:String="",
    val phoneNumber:String="",
    val isAddingContact:Boolean=false,
    val sortType: SortType =SortType.FIRST_NAME
)