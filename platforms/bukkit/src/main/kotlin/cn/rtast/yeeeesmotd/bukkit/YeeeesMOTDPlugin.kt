/*
 * Copyright 2024 RTAkland
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */


package cn.rtast.yeeeesmotd.bukkit

import cn.rtast.yeeeesmotd.bukkit.command.YeeeesMOTDCommand
import cn.rtast.yeeeesmotd.bukkit.events.PlayerJoinEventListener
import cn.rtast.yeeeesmotd.bukkit.events.ServerListPingEventListener
import cn.rtast.yeeeesmotd.utils.file.*
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin

class YeeeesMOTDPlugin : JavaPlugin() {

    companion object {
        val miniMessage: MiniMessage = MiniMessage.miniMessage()

        val faviconManager: FaviconManager = FaviconManager()
        val skinHeadManager: SkinHeadManager = SkinHeadManager()
        val pingRecordManager: PingRecordManager = PingRecordManager()
        val configManager: ConfigManager = ConfigManager()
        val hitokotoManager = HitokotoManager(configManager.hitokoto().type)
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(ServerListPingEventListener(server), this)
        server.pluginManager.registerEvents(PlayerJoinEventListener(server), this)

        this.getCommand("yeeeesmotd")?.setExecutor(YeeeesMOTDCommand())
    }
}
