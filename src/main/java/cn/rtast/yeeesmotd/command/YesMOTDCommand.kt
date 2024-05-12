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


package cn.rtast.yeeesmotd.command

import cn.rtast.yeeesmotd.YeeeesMOTDPlugin
import cn.rtast.yeeesmotd.listeners.ServerPreConnectEventListener
import com.velocitypowered.api.command.SimpleCommand
import net.kyori.adventure.text.Component

class YesMOTDCommand : SimpleCommand {

    override fun execute(invocation: SimpleCommand.Invocation) {
        val source = invocation.source()

        val args = invocation.arguments()

        if (args.isEmpty()) {
            source.sendMessage(
                Component.text(
                    "No valid args were found. Do /yesmotd reload to reload config or /yesmotd clear to clear player's head cache"
                )
            )
            return
        }

        if (args.first() == "reload") {
            YeeeesMOTDPlugin.faviconManager.setValidIcons()
            val config = YeeeesMOTDPlugin.configManager.pingFirst()
            ServerPreConnectEventListener.PING_FIRST_TEXT = config.pingFirstText
            ServerPreConnectEventListener.RE_PING_TEXT = config.rePingText
            source.sendMessage(Component.text("Successfully reloaded"))
            return
        }

        if (args.first() == "clear") {
            YeeeesMOTDPlugin.skinHeadManager.clear()
            source.sendMessage(Component.text("Successfully cleared"))
            return
        }

        source.sendMessage(
            Component.text(
                "No valid args were found. Do /yesmotd reload to reload config or /yesmotd clear to clear player's head cache"
            )
        )
    }
}