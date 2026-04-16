package pl.sirox.nxrtchat.service

import java.util.UUID

class PlayerService {

    private val lastMsg = mutableMapOf<UUID, UUID>()
    private val lastMessage = mutableMapOf<UUID, Long>()

    fun getLastMsg(uuid: UUID): UUID? {
        return lastMsg[uuid]
    }

    fun getLastMessage(uuid: UUID): Long? {
        return lastMessage[uuid]
    }

    fun setLastMsg(senderUUID: UUID, targetUUID: UUID) {
        lastMsg[senderUUID] = targetUUID
    }

    fun setLastMessage(senderUUID: UUID, time: Long) {
        lastMessage[senderUUID] = time
    }

}