package com.seph_worker.worker.core.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Query;

import java.util.*;

public class QueryUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public QueryUtils() {
    }

    public static void setResultTransformer(Query query) {
        setResultTransformer(query, Collections.emptyList(), Collections.emptyList());
    }
    public static void setResultTransformerWithExcludeColumns(Query query,List<String> excludeColumns) {
        setResultTransformer(query, Collections.emptyList(), excludeColumns);
    }

    public static void setResultTransformerWithJsonObjectAliases(Query query,List<String> jsonObjectAliases) {
        setResultTransformer(query, jsonObjectAliases, Collections.emptyList());
    }
    public static void setResultTransformer(Query query, List<String> jsonObjectAliases, List<String> excludeColumns) {

        ((org.hibernate.query.Query) query.unwrap(org.hibernate.query.Query.class))
                .setTupleTransformer((values, aliases) -> {
                    HashMap<String, Object> result = new HashMap<>();

                    for (int i = 0; i < values.length; ++i) {
                        String alias = aliases[i];
                        if (!excludeColumns.contains(alias)) {
                            processValue(values, aliases, jsonObjectAliases, result, i);
                        }
                    }

                    return result;
                });
    }

    private static void processValue(Object[] values, String[] aliases, List<String> jsonObjectAliases, Map<String, Object> result, int index) {
        if (values[index] != null) {
            try {
                values[index] = parseJsonValue(values[index], jsonObjectAliases, aliases[index]);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error parsing JSON value", e);
            }
        }

        String camelCaseKey = toCamelCase(aliases[index]);
        result.put(camelCaseKey, values[index]);
    }

    private static Object parseJsonValue(Object value, List<String> jsonObjectAliases, String alias) throws JsonProcessingException {
        if (jsonObjectAliases.contains(alias)) {
            try {
                return objectMapper.readValue(value.toString(), Map.class);
            } catch (JsonProcessingException var4) {
                return objectMapper.readValue(value.toString(), List.class);
            }
        } else {
            return value;
        }
    }

    private static String toCamelCase(String snakeCase) {
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for(char c : snakeCase.toCharArray()) {
            if (c == '_') {
                nextUpper = true;
            } else {
                result.append(nextUpper ? Character.toUpperCase(c) : c);
                nextUpper = false;
            }
        }

        return result.toString();
    }
}
