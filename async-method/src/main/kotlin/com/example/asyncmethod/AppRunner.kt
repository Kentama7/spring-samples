package com.example.asyncmethod

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AppRunner(
    private val gitHubLookupService: GitHubLookupService
) : CommandLineRunner {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AppRunner::class.java)
    }

    override fun run(vararg args: String) {
        val start = System.currentTimeMillis()
        // Kick of multiple, asynchronous lookups
        val page1 = gitHubLookupService.findUser("PivotalSoftware")
        val page2 = gitHubLookupService.findUser("CloudFoundry")
        val page3 = gitHubLookupService.findUser("Spring-Projects")
        // Wait until they are all done
        CompletableFuture.allOf(page1, page2, page3).join()

        logger.info("Elapsed time: ${System.currentTimeMillis() - start}")
        logger.info("--> ${page1.get()}")
        logger.info("--> ${page2.get()}")
        logger.info("--> ${page3.get()}")
    }
}