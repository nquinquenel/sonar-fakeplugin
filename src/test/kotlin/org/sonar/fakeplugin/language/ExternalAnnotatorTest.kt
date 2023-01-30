package org.sonar.fakeplugin.language

import com.intellij.psi.impl.source.PsiImportStatementImpl
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl
import com.intellij.psi.impl.source.tree.java.PsiPackageStatementImpl
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.junit.Test
import org.sonar.fakeplugin.BaseLightJavaCodeFixture

class ExternalAnnotatorTest : BaseLightJavaCodeFixture() {

    private val externalAnnotator = ExternalAnnotator()

    @Test
    fun checkJavaAnnotatorHightlights() {
        val file = myFixture.configureByFile("AttributeData.java")
        val elements = externalAnnotator.doAnnotate(file)

        assertThat(elements).isNotNull
        assertThat(elements).hasSize(3)
        assertThat(elements!![0].element::class.java).isEqualTo(PsiImportStatementImpl::class.java)
        assertThat(elements[1].element::class.java).isEqualTo(PsiPackageStatementImpl::class.java)
        assertThat(elements[2].element::class.java).isEqualTo(PsiLiteralExpressionImpl::class.java)
    }

    @Test
    fun checkKotlinAnnotatorHightlights() {
        val file = myFixture.configureByFile("ImportAndPackageData.kt")
        val elements = externalAnnotator.doAnnotate(file)

        assertThat(elements).isNotNull
        assertThat(elements).hasSize(2)
        assertThat(elements!![0].element::class.java).isEqualTo(KtImportDirective::class.java)
        assertThat(elements[1].element::class.java).isEqualTo(KtPackageDirective::class.java)
    }

    @Test
    fun checkNullFileAnnotatorHightlights() {
        val elements = externalAnnotator.doAnnotate(null)

        assertThat(elements).isNull()
    }

}