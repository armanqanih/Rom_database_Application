package com.ainarm.database_with_phlip.state

import com.ainarm.database_with_phlip.SortType
import com.ainarm.database_with_phlip.model.Contact

sealed interface ContactEvent {

    object SaveContact:ContactEvent
    data class SetFirstName(val firstName:String):ContactEvent
    data class SetLastName(val lastName:String):ContactEvent
    data class SetPhoneNumber(val phoneNumber:String):ContactEvent
    object ShowDialog:ContactEvent
    object HideDialog:ContactEvent
    data class SortByName(val sortType:SortType):ContactEvent
    data class DeleteContact(val contact:Contact):ContactEvent
    data class UpdateContact(val contact: Contact):ContactEvent

}