package com.cf.jkodiwrapper.test.application

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)

@Suite.SuiteClasses(AddonsTest::class, ApplicationTest::class, AudioTest::class, FavouritesTest::class, FilesTest::class, GUITest::class, InputTest::class,
        PlayerTest::class, PlaylistTest::class, ProfilesTest::class, SystemTest::class, VideoTest::class)
class TestSuite