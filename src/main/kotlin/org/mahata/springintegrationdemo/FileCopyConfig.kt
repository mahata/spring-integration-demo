package org.mahata.springintegrationdemo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.core.MessageSource
import org.springframework.integration.file.FileReadingMessageSource
import org.springframework.integration.file.FileWritingMessageHandler
import org.springframework.integration.file.filters.SimplePatternFileListFilter
import org.springframework.integration.file.support.FileExistsMode
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import java.io.File

@Configuration
@EnableIntegration
class FileCopyConfig {
    companion object {
        const val INPUT_DIR: String = "/tmp/source"
        const val OUTPUT_DIR: String = "/tmp/target"
        const val FILE_PATTERN: String = "*.jpg"
    }

    @Bean
    fun fileChannel(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = Poller(fixedDelay = "1000"))
    fun fileReadingMessageSource(): MessageSource<File> {
        val sourceReader = FileReadingMessageSource()
        sourceReader.setDirectory(File(INPUT_DIR))
        sourceReader.setFilter(SimplePatternFileListFilter(FILE_PATTERN))

        return sourceReader
    }

    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    fun fileWritingMessageHandler(): MessageHandler {
        val handler = FileWritingMessageHandler(File(OUTPUT_DIR))
        handler.setFileExistsMode(FileExistsMode.REPLACE)
        handler.setExpectReply(false)

        return handler
    }
}
