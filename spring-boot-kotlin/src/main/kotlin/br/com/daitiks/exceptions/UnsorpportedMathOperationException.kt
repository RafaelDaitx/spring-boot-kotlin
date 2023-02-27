package br.com.daitiks.exceptions
import java.lang.*
import kotlin.RuntimeException

class UnsorpportedMathOperationException(exception: String?) : RuntimeException(exception) {
}