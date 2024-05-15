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


package cn.rtast.yeeesmotd.entity

data class Config(
    val schemaVersion: Double,
    val pingPass: PingPass,
    val hitokoto: Hitokoto,
    val maximumPlayer: Int,
    val onlinePlayer: Int,
    val clearSamplePlayer: Boolean,
    val descriptions: MutableList<Description>,
) {
    data class PingPass(
        val enabled: Boolean,
        val pingFirstText: String,
        val pingAgainText: String,
        val interval: Int,
    )

    data class Description(
        val line1: String,
        val line2: String,
    )

    data class Hitokoto(
        val enabled: Boolean,
        val color: String,
        val type: String,
        val probability: Int,
    )
}