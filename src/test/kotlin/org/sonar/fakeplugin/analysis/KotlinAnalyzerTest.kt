package org.sonar.fakeplugin.analysis

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.junit.Test
import org.sonar.fakeplugin.BaseLightJavaCodeFixture

class KotlinAnalyzerTest : BaseLightJavaCodeFixture() {

    private val kotlinAnalyzer = KotlinAnalyzer

    @Test
    fun parseFileTest() {
        val testFile = myFixture.configureByFile("ImportAndPackageData.kt")

        val elements = kotlinAnalyzer.parseFile(testFile)

        assertThat(elements).hasSize(2)
        assertThat(elements[0].element::class.java).isEqualTo(KtImportDirective::class.java)
        assertThat(elements[1].element::class.java).isEqualTo(KtPackageDirective::class.java)
    }

}