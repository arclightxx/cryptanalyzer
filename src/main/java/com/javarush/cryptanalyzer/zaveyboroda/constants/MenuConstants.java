package com.javarush.cryptanalyzer.zaveyboroda.constants;

import java.util.HashMap;
import java.util.Map;

public class MenuConstants {
    public static final Map<Integer, String> MODES = new HashMap<>();
    static {
        MODES.put(1, "Шифрование");
        MODES.put(2, "Расшифровка");
        MODES.put(3, "Взлом");
    }
    private static final String GREETINGS_MESSAGE = "Вас приветстует приложение по работе с шифром Цезаря!\n";
    private static final String APP_INFO = "Приложение поддерживает 3 режима:\n";
    private static final String FIRST_MODE_INFO = "1. Шифрование - программа зашифровывает текст, используя случайно сгенерированный ключ\n";
    private static final String SECOND_MODE_INFO = "2. Расшифровка - программа расшифровывает текст, используя заданный ключ\n";
    private static final String THIRD_MODE_INFO = "3. Взлом - программа взламывает зашифрованный текст\n";
    private static final String MODE_INPUT_MESSAGE = "Пожалуйста, выберите желаемый режим, используя цифры 1, 2 и 3";
    public static final String MENU_MESSAGE = GREETINGS_MESSAGE + APP_INFO + FIRST_MODE_INFO + SECOND_MODE_INFO + THIRD_MODE_INFO + MODE_INPUT_MESSAGE;
}
