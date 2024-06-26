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


package cn.rtast.yeeeesmotd.utils.file

import cn.rtast.yeeeesmotd.ROOT_PATH
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.random.Random

class FaviconManager {

    private val iconsPath = File(ROOT_PATH, "icons")
    private var icons = iconsPath.listFiles()

    private val validIcons = mutableListOf<File>()

    init {
        if (!iconsPath.exists()) {
            iconsPath.mkdirs()
        }
    }

    fun getRandomIcon(): BufferedImage? {
        if (this.validIcons.isEmpty()) {
            return null
        }
        val rnd = Random.nextInt(0, validIcons.size)
        val icon = validIcons[rnd]
        val byteArray = icon.readBytes()
        return ImageIO.read(byteArray.inputStream())
    }

    fun setValidIcons() {
        this.validIcons.clear()
        icons = iconsPath.listFiles()
        if (icons?.isNotEmpty() == true) {
            for (i in icons) {
                if (i.isFile && i.extension == "png") {
                    this.validIcons.add(i)
                }
            }
        } else {
            println("No icons found")
        }
    }

}