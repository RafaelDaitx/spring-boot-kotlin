package br.com.daitiks.controller

import br.com.daitiks.exceptions.UnsorpportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController1 {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ):
            Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        if (firstNumber < secondNumber)
            throw UnsorpportedMathOperationException("You can't be set a number two higher than number one")
        return firstNumber - secondNumber
    }

    @RequestMapping(value = ["/multiplication/{numberOne}/{numberTwo}"])
    fun multiplication(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        return firstNumber * secondNumber
    }

    @RequestMapping(value = ["/division/{numberOne}/{numberTwo}"])
    fun division(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")

        return firstNumber / secondNumber
    }

    @RequestMapping(value = ["/mean/{numberOne}/{numberTwo}"])
    fun mean(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ):Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        return (firstNumber + secondNumber) / 2
    }

    @RequestMapping(value = ["/sqquareRoot/{number}"])
    fun sqquareRoot(
        @PathVariable(value = "number") numberOne: String?,
    ):Double {
        val firstNumber: Double = convertToDouble(numberOne)

        if (!isNumeric(numberOne))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        return Math.sqrt(firstNumber)
    }

    private fun convertToDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank()) return 0.0
        val number = strNumber.replace(",".toRegex(), ".")

        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if (strNumber.isNullOrBlank()) return false

        val number = strNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}