package com.seph_worker.worker.core.dto;

import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelParserService {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_TIME_SECONDS_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public <T> List<T> parseExcelToDTO(MultipartFile file, Class<T> dtoClass) {
        List<T> list = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                T dto = createAndPopulateDTO(row, dtoClass);
                if (dto != null) {
                    list.add(dto);
                }
            }
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }

        if(list.isEmpty())
            throw new ResourceNotFoundException("No se encontro informacion en el excel");
        return list;
    }

    private <T> T createAndPopulateDTO(Row row, Class<T> dtoClass) {
        try {
            T dto = dtoClass.getDeclaredConstructor().newInstance();
            Field[] fields = dtoClass.getDeclaredFields();

            for (Field field : fields) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    Cell cell = row.getCell(annotation.index());
                    Object value = getCellValue(cell, field.getType());
                    field.set(dto, value);
                }
            }
            return dto;
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
    }

    private Object getCellValue(Cell cell, Class<?> fieldType) {
        if (cell == null) return null;
        try {
            switch (cell.getCellType()) {
                case FORMULA:
                    return handleFormulaCell(cell, fieldType);
                case NUMERIC:
                    if (fieldType == Integer.class) {
                        return (int) cell.getNumericCellValue();
                    } else if (fieldType == Double.class) {
                        return cell.getNumericCellValue();
                    } else if (DateUtil.isCellDateFormatted(cell)) {
                        return fieldType == LocalDate.class
                                ? cell.getLocalDateTimeCellValue().toLocalDate()
                                : cell.getLocalDateTimeCellValue();
                    }
                    break;
                case STRING:
                    String value = cell.getStringCellValue().trim();
                    if (fieldType == Integer.class) {
                        return value.isEmpty() ? null : Integer.parseInt(value);
                    } else if (fieldType == Double.class) {
                        return value.isEmpty() ? null : Double.parseDouble(value);
                    } else if (fieldType == LocalDate.class || fieldType == LocalDateTime.class) {
                        return parseDateFromString(value, fieldType);
                    }
                    return value;
                case BOOLEAN:
                    return cell.getBooleanCellValue();
                case BLANK:
                    return null;
                default:
                    return null;
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException( e);
        }
        return null;
    }

    private Object handleFormulaCell(Cell cell, Class<?> fieldType) {
        try {
            FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
            CellValue cellValue = evaluator.evaluate(cell);

            switch (cellValue.getCellType()) {
                case NUMERIC:
                    if (fieldType == Integer.class) {
                        return (int) cellValue.getNumberValue();
                    } else if (fieldType == Double.class) {
                        return cellValue.getNumberValue();
                    } else if (fieldType == LocalDate.class) {
                        return cellValue.getNumberValue() != 0
                                ? LocalDate.ofEpochDay((long) cellValue.getNumberValue())
                                : null;
                    } else if (fieldType == LocalDateTime.class) {
                        return cellValue.getNumberValue() != 0
                                ? LocalDateTime.ofEpochSecond((long) cellValue.getNumberValue(), 0, java.time.ZoneOffset.UTC)
                                : null;
                    }
                    break;
                case STRING:
                    String value = cellValue.getStringValue().trim();
                    if (fieldType == Integer.class) {
                        return value.isEmpty() ? null : Integer.parseInt(value);
                    } else if (fieldType == Double.class) {
                        return value.isEmpty() ? null : Double.parseDouble(value);
                    } else if (fieldType == LocalDate.class || fieldType == LocalDateTime.class) {
                        return parseDateFromString(value, fieldType);
                    }
                    return value;
                case BOOLEAN:
                    return cellValue.getBooleanValue();
                default:
                    return null;
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException( e);
        }
        return null;
    }

    private Object parseDateFromString(String text, Class<?> fieldType) {
        if (fieldType == LocalDate.class) {
            return LocalDate.parse(text, DATE_FORMAT);
        } else if (fieldType == LocalDateTime.class) {
            if (text.length() <= 10) {
                return LocalDate.parse(text, DATE_FORMAT).atStartOfDay();
            } else if (text.length() <= 16) {
                return LocalDateTime.parse(text, DATE_TIME_FORMAT);
            } else {
                return LocalDateTime.parse(text, DATE_TIME_SECONDS_FORMAT);
            }
        }
        return null;
    }
}