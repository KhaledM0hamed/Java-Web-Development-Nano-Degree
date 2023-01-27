package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.MissingDataException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

@Service
public class ValidationService {

    public void validatePOJOAttributesNotNullOrEmpty(Object pojo) throws MissingDataException {
        String missing = "";
        Method[] methods = pojo.getClass().getMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    Object o = method.invoke(pojo);

                    // if not valid (null or empty list/set) get the field name for exception message.
                    if ((o == null) ||
                            (o instanceof List && ((List) o).size() == 0) ||
                            (o instanceof Set && ((Set) o).size() == 0)) {
                        String name = method.getName().substring(3);
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);
                        missing += (missing.length() > 0) ? ", " + name : name;
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException exception) {
                System.out.println(exception.getMessage());
            }
        }
        if (missing.length() > 0) {
            throw new MissingDataException("Missing request parameter(s): " + missing + ".");
        }
    }

}
