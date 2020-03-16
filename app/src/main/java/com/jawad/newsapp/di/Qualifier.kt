package com.jawad.newsapp.di

import javax.inject.Qualifier

/**
 * The class Qualifier
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NewAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeIO