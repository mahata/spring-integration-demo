package org.mahata.springintegrationdemo

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel

@SpringBootTest
class FileCopyConfigTest {
    @Autowired
    private lateinit var fileChannel: MessageChannel

    @Test
    fun `fileChannel is a DirectChannel`() {
        assertTrue(fileChannel is DirectChannel)
    }
}
