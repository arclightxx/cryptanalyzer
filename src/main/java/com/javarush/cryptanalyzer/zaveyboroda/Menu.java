package com.javarush.cryptanalyzer.zaveyboroda;

public class Menu {
    private static final String GREETINGS_MESSAGE = "Вас приветстует приложение по работе с шифром Цезаря!\n";
    private static final String APP_INFO = "Приложение поддерживает 3 режима:\n";
    private static final String FIRST_MODE_INFO = "1. Шифрование - программа зашифровывает текст, используя случайно сгенерированный ключ\n";
    private static final String SECOND_MODE_INFO = "2. Расшифровка - программа расшифровывает текст, используя заданный ключ\n";
    private static final String THIRD_MODE_INFO = "3. Взлом - программа взламывает зашифрованный текст\n";
    private static final String MODE_INPUT_MESSAGE = "Пожалуйста, введите желаемый режим работы, используя цифры 1, 2 и 3";
    public static final String MENU_MESSAGE = GREETINGS_MESSAGE + APP_INFO + FIRST_MODE_INFO + SECOND_MODE_INFO + THIRD_MODE_INFO + MODE_INPUT_MESSAGE;
    public static String MODE_INFO_MESSAGE = "Вы выбрали режим %s. Пожалуйста, введите путь к исходному текстовому файлу или введите 0, чтобы выбрать файл по умолчанию";
}
