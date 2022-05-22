package calculator.controllers;

import calculator.service.Calculator;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// Clase que contiene el menú de la calculadora
public class Menu {
    Scanner sc = new Scanner(System.in);
    Logger logger = Logger.getLogger(Calculator.class.getSimpleName());

    // Instancia de la clase Calculadora
    private Calculator calculator;

    // Método que inicia la calculadora
    public void start() {
        int selection;

        // Ciclo Do-While para mantener el menú funcionando hasta que el usuario desee salirse
        do {
            selection = getSelection();

            // Condicional Switch para elegir cada una de las operaciones
            switch (selection) {
                case 1 -> {
                    logger.log(Level.INFO, "Cuantos números desea ingresar?");
                    int numSize = sc.nextInt();
                    this.calculator = new Calculator(numSize);
                    this.calculator.addNumbers();
                }
                case 2 -> this.calculator.addition();
                case 3 -> this.calculator.subtraction();
                case 4 -> this.calculator.multiplication();
                case 5 -> this.calculator.division();
                case 6 -> this.calculator.remainder();
                case 7 -> logger.log(Level.INFO, "Hasta la próxima!");
                default -> logger.log(Level.INFO, "Opción equivocada, intentelo de nuevo");
            }
        } while (selection != 7);
    }

    // Método privado con las opciones del menú
    private int getSelection() {
        int selection;
        String calculatorOptions = """
                Bienvenido a la calculadora, por favor seleccione una opción:\040
                1. Añadir números
                2. Sumar
                3. Restar
                4. Multiplicar
                5. Dividir
                6. Módulo de división
                7. Salir
                """;
        logger.log(Level.INFO, calculatorOptions);
        selection = sc.nextInt();
        return selection;
    }
}
