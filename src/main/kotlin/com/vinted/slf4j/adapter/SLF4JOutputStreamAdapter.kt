package com.vinted.slf4j.adapter

import org.slf4j.helpers.MarkerIgnoringBase
import org.slf4j.helpers.MessageFormatter
import org.slf4j.spi.LocationAwareLogger.*
import java.io.OutputStream
import java.io.PrintStream


/**
 * @param defaultOutputStream is [OutputStream] which is used for all enabled log levels.
 * For example [System.out] or [System.err].
 *
 * This class is inspired by [SimpleLogger](https://github.com/qos-ch/slf4j/blob/master/slf4j-simple/src/main/java/org/slf4j/simple/SimpleLogger.java)
 */
class SLF4JOutputStreamAdapter @JvmOverloads constructor(
    defaultOutputStream: OutputStream,
    private val logLevel: Int = TRACE_INT
) : MarkerIgnoringBase() {

    private val defaultOutputStream = PrintStream(defaultOutputStream)

    private fun log(level: Int, message: String?, t: Throwable?) {
        if (!isLevelEnabled(level)) return

        val buf = StringBuilder(32)
            .append(renderLevel(level))
            .append(": ")
            .append(message ?: "")
            .append("\n")

        defaultOutputStream.println(buf.toString())

        t?.printStackTrace(defaultOutputStream)

        defaultOutputStream.flush()
    }

    private fun renderLevel(level: Int): String {
        when (level) {
            TRACE_INT -> return "TRACE"
            DEBUG_INT -> return "DEBUG"
            INFO_INT -> return "INFO"
            WARN_INT -> return "WARN"
            ERROR_INT -> return "ERROR"
        }
        throw IllegalStateException("Unrecognized level [$level]")
    }

    /**
     * Is the given log level currently enabled?
     *
     * @param logLevel
     * is this level enabled?
     */
    private fun isLevelEnabled(logLevel: Int): Boolean {
        // log level are numerically ordered so can use simple numeric
        // comparison
        return logLevel >= this.logLevel
    }


    override fun isErrorEnabled() = isLevelEnabled(ERROR_INT)

    override fun isDebugEnabled() = isLevelEnabled(DEBUG_INT)

    override fun isInfoEnabled() = isLevelEnabled(INFO_INT)

    override fun isWarnEnabled() = isLevelEnabled(WARN_INT)

    override fun isTraceEnabled() = isLevelEnabled(ERROR_INT)

    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arg1
     * @param arg2
     */
    private fun formatAndLog(level: Int, format: String?, arg1: Any?, arg2: Any?) {
        if (!isLevelEnabled(level)) {
            return
        }
        val tp = MessageFormatter.format(format, arg1, arg2)
        log(level, tp.message, tp.throwable)
    }

    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arguments
     * a list of 3 ore more arguments
     */
    private fun formatAndLog(level: Int, format: String?, vararg arguments: Any?) {
        if (!isLevelEnabled(level)) {
            return
        }
        val tp = MessageFormatter.arrayFormat(format, arguments)
        formatAndLog(level, tp.message, tp.throwable)
    }

    override fun warn(msg: String?) {
        log(WARN_INT, msg, null)
    }

    override fun warn(format: String?, arg: Any?) {
        formatAndLog(WARN_INT, format, arg, null)
    }

    override fun warn(format: String?, vararg arguments: Any?) {
        formatAndLog(WARN_INT, format, *arguments)
    }

    override fun warn(format: String?, arg1: Any?, arg2: Any?) {
        formatAndLog(WARN_INT, format, arg1, arg2)
    }

    override fun warn(msg: String?, t: Throwable?) {
        log(WARN_INT, msg, t)
    }

    override fun info(msg: String?) {
        log(INFO_INT, msg, null)
    }

    override fun info(format: String?, arg: Any?) {
        formatAndLog(INFO_INT, format, arg, null)
    }

    override fun info(format: String?, arg1: Any?, arg2: Any?) {
        formatAndLog(INFO_INT, format, arg1, arg2)
    }

    override fun info(format: String?, vararg arguments: Any?) {
        formatAndLog(INFO_INT, format, *arguments)
    }

    override fun info(msg: String?, t: Throwable?) {
        log(INFO_INT, msg, t)
    }

    override fun error(msg: String?) {
        log(ERROR_INT, msg, null)
    }

    override fun error(format: String?, arg: Any?) {
        formatAndLog(ERROR_INT, format, arg, null)
    }

    override fun error(format: String?, arg1: Any?, arg2: Any?) {
        formatAndLog(ERROR_INT, format, arg1, arg2)
    }

    override fun error(format: String?, vararg arguments: Any?) {
        formatAndLog(ERROR_INT, format, *arguments)
    }

    override fun error(msg: String?, t: Throwable?) {
        log(ERROR_INT, msg, t)
    }

    override fun debug(msg: String?) {
        log(DEBUG_INT, msg, null)
    }

    override fun debug(format: String?, arg: Any?) {
        formatAndLog(DEBUG_INT, format, arg, null)
    }

    override fun debug(format: String?, arg1: Any?, arg2: Any?) {
        formatAndLog(DEBUG_INT, format, arg1, arg2)
    }

    override fun debug(format: String?, vararg arguments: Any?) {
        formatAndLog(DEBUG_INT, format, *arguments)
    }

    override fun debug(msg: String?, t: Throwable?) {
        log(DEBUG_INT, msg, t)
    }

    override fun trace(msg: String?) {
        log(TRACE_INT, msg, null)
    }

    override fun trace(format: String?, arg: Any?) {
        formatAndLog(TRACE_INT, format, arg, null)
    }

    override fun trace(format: String?, arg1: Any?, arg2: Any?) {
        formatAndLog(TRACE_INT, format, arg1, arg2)
    }

    override fun trace(format: String?, vararg arguments: Any?) {
        formatAndLog(TRACE_INT, format, *arguments)
    }

    override fun trace(msg: String?, t: Throwable?) {
        log(TRACE_INT, msg, t)
    }
}
