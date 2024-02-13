package org.mahata.springintegrationdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.AbstractApplicationContext
import java.util.Scanner
import kotlin.system.exitProcess

@SpringBootApplication
class SpringIntegrationDemoApplication

fun main() {
    val context: AbstractApplicationContext = AnnotationConfigApplicationContext(FileCopyConfig::class.java)
    context.registerShutdownHook()

    val scanner = Scanner(System.`in`)
    print("Press 'q' and <enter> to stop the program: ")
    while (true) {
        val input = scanner.nextLine()
        if ("q" == input.trim { it <= ' ' }) {
            context.close()
            scanner.close()
            break
        }
    }

    exitProcess(0)
}
