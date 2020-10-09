package me.ishanjoshi.chart_accessibility_module


import org.apache.commons.math3.fitting.HarmonicCurveFitter
import org.apache.commons.math3.fitting.PolynomialCurveFitter
import org.apache.commons.math3.fitting.WeightedObservedPoints
import org.apache.commons.math3.stat.regression.SimpleRegression
import java.util.*
import kotlin.math.sin

data class ChartPoint(val x: Double, val y: Double)


fun main() {
//    sineInterpolationCheck()
//    polynomialFitter()
//    linearRegressionFitter()

    val pcbd = PieChartDescriptor(
            arrayOf("iOS", "Android", "Windows", "Symbian"),
            arrayOf(0.51f, 0.45f, 0.03f, 0.01f, 0.02f),
            "Operating system",
            numValuesToRead = 2
    ).describe()

    val pcbds = PieChartDescriptor(
            "Maruti,Hyundai,Mahindra,Tata,Honda,Toyota,Renault,Ford".split(",").toTypedArray(),
            arrayOf(0.52f, 0.17f, 0.08f, 0.07f, 0.05f, 0.04f, 0.03f, 0.04f),
            "Automobile Companies"
    ).describe()

    println(pcbd)
    println(pcbds)

    val barChartDescriptorDescription = BarChartDescriptor(
            arrayOf("Jazz", "City", "Accord", "HRV"),
            arrayOf(333f, 3223f, 234f, 342f),
            "Car model",
            "sale count",
            "Honda Car model sales count for 2020"
    ).describe()

    println(barChartDescriptorDescription)

}

fun sineInterpolationCheck() {
    val array = Array(10) { pos -> ChartPoint(pos.toDouble(), Math.random() * 0.1 + sin(pos.toDouble())) }

    val weightedObservedPoints = WeightedObservedPoints()
    array.forEach { chartPoint -> weightedObservedPoints.add(chartPoint.x, chartPoint.y) }

    val harmonicCurveFitter = HarmonicCurveFitter.create()

    val res = harmonicCurveFitter.fit(weightedObservedPoints.toList())

    weightedObservedPoints.toList().forEach { point -> print("[${point.x}, ${point.y}]") }
    println("")
    println("Sine interpolation is ${Arrays.toString(res)}")
}

fun polynomialFitter() {
    val observedPoints = WeightedObservedPoints()

    for (i in 1..10) {
        observedPoints.add(i.toDouble(), i * i.toDouble())
    }

    val polynomialCurveFitter = PolynomialCurveFitter.create(2)
    val res = polynomialCurveFitter.fit(observedPoints.toList())

    println(Arrays.toString(res))

}


fun linearRegressionFitter() {
    val simpleRegression = SimpleRegression(true)
    for (i in 1..100) {
        simpleRegression.addData(i.toDouble(), Math.random() * 5 + i)
    }

    println(simpleRegression)
}


