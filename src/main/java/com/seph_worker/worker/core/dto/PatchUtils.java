package com.seph_worker.worker.core.dto;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

@AllArgsConstructor
@Component
public class PatchUtils {
    public static void copyNonNullProperties(Object source, Object target) {
        String[] nullProps = Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .map(PropertyDescriptor::getName)
                .filter(name -> {
                    try {
                        Object value = BeanUtils.getPropertyDescriptor(source.getClass(), name)
                                .getReadMethod()
                                .invoke(source);
                        return value == null;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toArray(String[]::new);

        BeanUtils.copyProperties(source, target, nullProps);
    }
}
