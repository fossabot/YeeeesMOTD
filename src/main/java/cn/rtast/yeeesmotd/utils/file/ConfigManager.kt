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


package cn.rtast.yeeesmotd.utils.file

import cn.rtast.yeeesmotd.*
import cn.rtast.yeeesmotd.entity.Config
import cn.rtast.yeeesmotd.entity.Description
import cn.rtast.yeeesmotd.entity.PingPass

class ConfigManager :
    IJsonManager<Config>(
        "config.json",
        Config(
            PingPass(false, PING_FIRST_TEXT, RE_PING_TEXT, DEFAULT_PING_INTERVAL),
            -1,
            -1,
            true,
            mutableListOf()
        )
    ) {

    init {
        val config = this.read()
        if (config.descriptions.isEmpty()) {
            config.descriptions.addAll(DEFAULT_DESCRIPTIONS)
            this.write(config)
        }
    }

    private fun read(): Config {
        val str = this.file.readText()
        return YeeeesMOTDPlugin.gson.fromJson(str, Config::class.java)
    }

    private fun write(config: Config) {
        val serData = YeeeesMOTDPlugin.gson.toJson(config)
        this.file.writeText(serData)
    }

    fun getRandomDescription(): Description? {
        val config = this.read()
        val descriptions = config.descriptions
        if (descriptions.isEmpty()) {
            return null
        }
        return descriptions.random()
    }

    fun getRandomBuildInDescription(): String {
        return BUILD_IN_DESCRIPTIONS.random()
    }

    fun pingPass(): PingPass {
        return this.read().pingPass
    }

    fun getConfig(): Config {
        return this.read()
    }
}