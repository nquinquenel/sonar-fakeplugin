package org.sonar.fakeplugin

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixture4TestCase
import java.nio.file.Paths

abstract class BaseLightJavaCodeFixture : LightPlatformCodeInsightFixture4TestCase() {

    override fun getTestDataPath(): String {
        return Paths.get("src/test/testData/").toAbsolutePath().toString()
    }

}