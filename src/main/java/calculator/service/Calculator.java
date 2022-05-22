package calculator.service;

import calculator.controllers.Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator implements Operations {

    // Clase principal de la calculadora, se implementa la interfaz Operations, la cual contiene los métodos requeridos

    Scanner sc = new Scanner(System.in);
    Logger logger = Logger.getLogger(Calculator.class.getSimpleName());

    // Se definen las variables de clase necesarias
    private final List<Double> calculatorList = new ArrayList<>();
    private final int calculatorListSize;

    // Constructor de clase con el parámetro del tamaño del arreglo
    public Calculator(int calculatorListSize) {
        this.calculatorListSize = calculatorListSize;
    }

    // Método para añadir números al arreglo calculatorList
    public void addNumbers() {
        for (int i = 0; i < this.calculatorListSize; i++) {
            logger.log(Level.INFO, "Ingrese el número " + (i + 1));
            double num = sc.nextDouble();
            this.calculatorList.add(num);
        }
    }

    /*
        Los métodos se desarrollaron utilizando streams para recorrer la lista y reduce para reducirla y realizar
        la operación necesaria. Se hizo de esta forma como práctica de programación funcional.
    */


    // Método para sumar los valores añadidos al arreglo
    @Override
    public void addition() {
        logger.log(Level.INFO, "Resultado suma: " + this.calculatorList.stream()
                .reduce(0.0, Double::sum));
    }

    // Método para restar los valores añadidos al arreglo
    @Override
    public void subtraction() {
        logger.log(Level.INFO, "Resultado resta: " + this.calculatorList.stream()
                .reduce((x, y) -> x - y)
                .get());
    }

    // Método para multiplicar los valores añadidos al arreglo
    @Override
    public void multiplication() {
        logger.log(Level.INFO, "Resultado multiplicación: " + this.calculatorList.stream()
                .reduce(1.0, (x, y) -> x * y));
    }

    /*
        Método para dividir los valores añadidos al arreglo, primero se válida que alguno de los valores sea igual
        a cero, de ser el caso se lanza el mensaje de alerta, luego se recorre el arreglo y se filtra el número 0
        para sacarlo de este y no arroje una excepción por este número. Al terminar se dividen todos los números y si
        existe un 0 se excluye

     */
    @Override
    public void division() {
        if (this.calculatorList.stream().anyMatch(x -> x == 0)){
            logger.log(Level.WARNING, "Error, no se puede dividir por cero, este número ha sido excluído");
        }
        logger.log(Level.INFO, "Resultado división: " + this.calculatorList.stream()
                .filter(x -> x != 0).reduce((x, y) -> x / y)
                .get());
    }

    // Método para extraer el módulo de la división de los valores añadidos al arreglo
    @Override
    public void remainder() {
        logger.log(Level.INFO, "Resultado del módulo: " + this.calculatorList.stream()
                .reduce((x, y) -> x % y)
                .get());
    }
}