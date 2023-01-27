package org.sonar.fakeplugin.analysis

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.application.ReadAction
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.idea.structuralsearch.visitor.KotlinRecursiveElementVisitor
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPackageDirective

class KotlinAnalyser {

    companion object {
        const val ANNOTATED_WORD = "SONAR"

        fun parseFile(file: PsiFile): List<AnalyzedElement> {
            val elements = mutableListOf<AnalyzedElement>()

            ReadAction.run<Exception> {
                file.accept(object : KotlinRecursiveElementVisitor() {
                    override fun visitImportDirective(importDirective: KtImportDirective) {
                        elements.add(AnalyzedElement(importDirective, HighlightSeverity.ERROR, "This is an import"))
                    }
                })

                file.accept(object : KotlinRecursiveElementVisitor() {
                    override fun visitPackageDirective(directive: KtPackageDirective) {
                        elements.add(AnalyzedElement(directive, HighlightSeverity.WARNING, "This is a package"))
                    }
                })

                file.accept(object : KotlinRecursiveElementVisitor() {
                    override fun visitKtElement(element: KtElement) {
                        super.visitKtElement(element)
                        element.text?. let {
                            if (it == ANNOTATED_WORD) elements.add(AnalyzedElement(element, HighlightSeverity.INFORMATION, "SONAR keyword"))
                        }
                    }
                })
            }

            return elements
        }
    }

}