package br.com.daitiks.controller

import br.com.daitiks.converters.NumberConverter
import br.com.daitiks.converters.NumberConverter.convertToDouble
import br.com.daitiks.exceptions.UnsorpportedMathOperationException
import br.com.daitiks.math.SimpleMath
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

//@RestController
class MathController {

    val counter: AtomicLong = AtomicLong()

    private val math: SimpleMath = SimpleMath()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        return math.sum(convertToDouble(numberOne), convertToDouble(numberTwo))
    }

    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        if (firstNumber < secondNumber)
            throw UnsorpportedMathOperationException("You can't be set a number two higher than number one")
        return math.subtractiob(firstNumber, secondNumber)
    }

    @RequestMapping(value = ["/multiplication/{numberOne}/{numberTwo}"])
    fun multiplication(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        return math.multiplication(firstNumber, secondNumber)
    }

    @RequestMapping(value = ["/division/{numberOne}/{numberTwo}"])
    fun division(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")

        return math.division(firstNumber, secondNumber)
    }

    @RequestMapping(value = ["/mean/{numberOne}/{numberTwo}"])
    fun mean(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)
        val secondNumber: Double = convertToDouble(numberTwo)

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")

        return math.mean(firstNumber, secondNumber)
    }

    @RequestMapping(value = ["/squareRoot/{number}"])
    fun squareRoot(
        @PathVariable(value = "number") numberOne: String?,
    ): Double {
        val firstNumber: Double = convertToDouble(numberOne)

        if (!NumberConverter.isNumeric(numberOne))
            throw UnsorpportedMathOperationException("Please, set a numeric value! ")
        return math.squareRoot(firstNumber)
    }
}