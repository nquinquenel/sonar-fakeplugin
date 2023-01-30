package org.sonar.fakeplugin.analysis

import com.intellij.psi.impl.source.PsiImportStatementImpl
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl
import com.intellij.psi.impl.source.tree.java.PsiPackageStatementImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.sonar.fakeplugin.BaseLightJavaCodeFixture

class JavaAnalyzerTest : BaseLightJavaCodeFixture() {

    private val javaAnalyzer = JavaAnalyzer

    @Test
    fun parseFileTest() {
        val testFile = myFixture.configureByFile("AttributeData.java")

        val elements = javaAnalyzer.parseFile(testFile)

        assertThat(elements).hasSize(3)
        assertThat(elements[0].element::class.java).isEqualTo(PsiImportStatementImpl::class.java)
        assertThat(elements[1].element::class.java).isEqualTo(PsiPackageStatementImpl::class.java)
        assertThat(elements[2].element::class.java).isEqualTo(PsiLiteralExpressionImpl::class.java)
    }

}