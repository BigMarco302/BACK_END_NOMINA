package com.seph_worker.worker.core.dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class toCaseString {

    public String toSnakeCase(String input) {
        if (input == null || input.isEmpty()) return input;

        String snake = input
                .replaceAll("([a-z])([A-Z])", "$1_$2")     // camelCase -> camel_Case
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2") // ABCWord -> ABC_Word
                .replaceAll("[-\\s]", "_")                  // espacios y guiones -> _
                .toLowerCase();                             // todo en minúsculas

        return snake;
    }
    public String toCamelCase(String input) {
        if (input == null || input.isEmpty()) return input;
        StringBuilder camelCase = new StringBuilder();
        boolean toUpper = false;

        for (char c : input.toCharArray()) {
            if (c == '_' || c == '-' || c == ' ') {
                toUpper = true;
            } else {
                camelCase.append(toUpper ? Character.toUpperCase(c) : Character.toLowerCase(c));
                toUpper = false;
            }
        }
        return camelCase.toString();
    }

}
