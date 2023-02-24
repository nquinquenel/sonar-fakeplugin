package org.sonar.fakeplugin.analysis

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.application.ReadAction
import com.intellij.psi.JavaRecursiveElementVisitor
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiImportStatement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiPackageStatement

class JavaAnalyzer {

    companion object {
        const val ANNOTATED_WORD = "\"SONAR\""

        fun parseFile(file: PsiFile): List<AnalyzedElement> {
            val elements = mutableListOf<AnalyzedElement>()

            ReadAction.run<Exception> {
                file.accept(object : JavaRecursiveElementVisitor() {
                    override fun visitImportStatement(statement: PsiImportStatement) {
                        elements.add(AnalyzedElement(statement, HighlightSeverity.ERROR, "This is an import"))
                    }
                })

                file.accept(object : JavaRecursiveElementVisitor() {
                    override fun visitPackageStatement(statement: PsiPackageStatement) {
                        elements.add(AnalyzedElement(statement, HighlightSeverity.WARNING, "This is a package"))
                    }
                })

                file.accept(object : JavaRecursiveElementVisitor() {
                    override fun visitLiteralExpression(element: PsiLiteralExpression) {
                        super.visitLiteralExpression(element)
                        if (element.text == ANNOTATED_WORD) elements.add(
                            AnalyzedElement(
                                element,
                                HighlightSeverity.INFORMATION,
                                "Sonar field"
                            )
                        )
                    }
                })
            }

            return elements
        }
    }

}