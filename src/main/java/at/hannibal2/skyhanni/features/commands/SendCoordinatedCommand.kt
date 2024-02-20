package at.hannibal2.skyhanni.features.commands

import at.hannibal2.skyhanni.events.MessageSendToServerEvent
import at.hannibal2.skyhanni.utils.ChatUtils
import at.hannibal2.skyhanni.utils.LocationUtils
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class SendCoordinatedCommand {

    @SubscribeEvent
    fun onMessageSendToServer(event: MessageSendToServerEvent) {
        val message = event.message
        if (message == "/sendcoords") {
            event.isCanceled = true
            ChatUtils.sendCommandToServer(getCoordinates())
        } else if (message.startsWith("/sendcoords ")) {
            event.isCanceled = true
            val description = message.split(" ").drop(1).joinToString(" ")
            ChatUtils.sendCommandToServer("${getCoordinates()} $description")
        }
    }

    private fun getCoordinates(): String {
        val location = LocationUtils.playerLocation()
        val x = location.x.toInt()
        val y = location.y.toInt()
        val z = location.z.toInt()
        return "x: $x, y: $y, z: $z"
    }
}
