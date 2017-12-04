package io.mockk.impl

import io.mockk.Answer
import io.mockk.Invocation
import io.mockk.Matcher
import io.mockk.MockKException
import kotlin.reflect.KClass

internal abstract class CallRecorderState(val recorder: CallRecorderImpl) {
    open fun call(invocation: Invocation): Any? = cancelAndThrowBadRecordingState()
    open fun startStubbing(): CallRecorderState = cancelAndThrowBadRecordingState()
    open fun startVerification(): CallRecorderState = cancelAndThrowBadRecordingState()
    open fun catchArgs(round: Int, n: Int): Unit = cancelAndThrowBadRecordingState()
    open fun answer(answer: Answer<*>): Unit = cancelAndThrowBadRecordingState()
    open fun <T : Any> matcher(matcher: Matcher<*>, cls: KClass<T>): T = cancelAndThrowBadRecordingState()
    open fun doneVerification(): CallRecorderState = cancelAndThrowBadRecordingState()
    open fun nCalls(): Int = cancelAndThrowBadRecordingState()
    open fun estimateCallRounds(): Int = cancelAndThrowBadRecordingState()

    private fun cancelAndThrowBadRecordingState(): Nothing {
        recorder.reset()
        throw MockKException("Bad recording sequence. State: ${recorder.state::class}")
    }
}