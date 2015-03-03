package com.br.ifpb.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 24/02/2015 ás 20:43:39
 */
public class ConverterInformacao {

    public static Timestamp converteTimestamp(String data) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, format);
        return Timestamp.valueOf(localDate);
    }

    public static Date converteDate(String data) {
        if (data != null) {
            if (data.equals("")) {
              return null;
            } else {
                if (data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(data, dateTimeFormatter);
                    return Date.valueOf(date);

                } else {
                    return Date.valueOf(data);

                }
            }
        }else return null;
    }

}
