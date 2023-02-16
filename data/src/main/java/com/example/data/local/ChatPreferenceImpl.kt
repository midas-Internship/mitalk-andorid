package com.example.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class ChatPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ChatPreference {
    override suspend fun saveChatType(chatType: String) =
        saveStringPreference(CHAT_TYPE, chatType)

    override suspend fun fetchChatType(): String =
        fetchStringPreference(CHAT_TYPE)

    override suspend fun clearChatType() =
        clearPreference(CHAT_TYPE)

    override suspend fun saveRoomId(roomId: String) =
        saveStringPreference(ROOM_ID, roomId)

    override suspend fun fetchRoomId(): String =
        fetchStringPreference(ROOM_ID)

    override suspend fun clearRoomId() =
        clearPreference(ROOM_ID)

    private fun fetchStringPreference(key: String): String =
        sharedPreferences.getString(key, null) ?: ""

    private fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    private fun clearPreference(key: String) =
        editPreference { it.remove(key) }


    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }

    companion object Key {
        const val CHAT_TYPE = "CHAT_TYPE"
        const val ROOM_ID = "ROOM_ID"
    }
}