package io.github.artemptushkin.breakingbad.scc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HeisenbergApplication

fun main(args: Array<String>) {
    runApplication<HeisenbergApplication>(*args)
}
