package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.factory.RuleFactory;
import com.ensao.gi5.lint.enumeration.RulesEnum;

public class Runner {
    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println("Usage example -s  D:/Test/Example.java");
            throw new IllegalStateException("arguments are empty");
        }

        String directory = null;

        for (int i = 0; i < args.length; i++) {
            String argument = args[i];
            if (argument.equals("-s")) {
                if (i == args.length - 1 || (directory = args[i + 1]).isEmpty()) {
                    System.out.println("Usage example : -s  D:/Test/Example.java");
                    throw new IllegalStateException("The directory or file are not specified");
                }
            }
        }
        final Linter linter = new Linter();
        final RuleFactory ruleFactory = new RuleFactory();
        for (RulesEnum rule: RulesEnum.values()) {
            linter.registerRule(ruleFactory.getRule(rule));
        }
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(directory);
        linter.run();
    }
}
