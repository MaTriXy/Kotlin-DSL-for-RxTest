package com.rubylichtenstein.rxtest.assertions

import com.rubylichtenstein.rxtest.matchers.Matcher
import com.rubylichtenstein.rxtest.matchers.never
import com.rubylichtenstein.rxtest.matchers.value
import io.reactivex.functions.Predicate
import io.reactivex.observers.BaseTestConsumer
import java.lang.AssertionError


infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.should(matcher: Matcher<BaseTestConsumer<T, U>>)
        = assertThat<T, U>(this, matcher)

infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.shouldHave(matcher: Matcher<BaseTestConsumer<T, U>>)
        = should(matcher)

infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.shouldBe(matcher: Matcher<BaseTestConsumer<T, U>>)
        = should(matcher)

infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.shouldEmit(matcher: Matcher<BaseTestConsumer<T, U>>)
        = should(matcher)

infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.shouldEmit(t: T)
        = shouldHave(value(t))

infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.shouldEmit(t: Predicate<T>)
        = shouldHave(value(t))

infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.shouldNeverEmit(t: T)
        = should(never(t))

infix fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.shouldNeverEmit(t: Predicate<T>)
        = should(never(t))

fun <T, U : BaseTestConsumer<T, U>> assertThat(baseTestConsumer: BaseTestConsumer<T, U>, matcher: Matcher<BaseTestConsumer<T, U>>) {
    with(matcher.test(baseTestConsumer)) {
        if (!passed) {
            throw AssertionError(failMessage)
        }
    }
}