package org.homework.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* нельзя расширятсья у класса
   простенький ApplicationContext который согласно application.properties
   получает зависимости, из них делает определённые объекты, сохраняет в кэще
   и в случае обращении отдаёт возвращает эти объекты
 */
public final class AppContext {

    // нельзя создавать экземпляр класса
    private AppContext() {

    }

    // ключ - название зависимости, значение - конкретная реализация зависимости
    private static Map<String, Object> beanStorage = new HashMap<>();
    private static Properties properties = new Properties();


    public static void load(final String fileName) throws IOException {
        // не корректно проверять что строка null, существуют проверки что на то что внутри строка не пустая
        if (fileName == null) throw new IllegalArgumentException("fileName must be set");

        try(InputStream is = AppContext.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(is);
        }

    }

    // не потоко безопасный
    public static <T> T getBean(final String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (beanName == null) throw new IllegalArgumentException("beanName must be set");

        // если конкретная реализация уже храниться в HashMap то сразу её возвращаем что бы не дублировать
        if (beanStorage.containsKey(beanName)) return (T) beanStorage.get(beanName);

        final String property = properties.getProperty(beanName);
        // если по такому ключу нету никакой зависимости
        if (property == null) throw new IllegalArgumentException("bean with name + " + beanName + " not found");

        /* получаем description класса что бы создать его instance
           не безопасно - Class.forName(property).newInstance()
           более безопасно сказать что у класса есть дефолтный конструктор
           пример - Class.forName(property).getDeclaredConstructor().newInstance()
         */
        final T bean = (T) Class.forName(property).newInstance();

        // добавляем его в hashmap что бы при следующем получении этого же bean он не создавался а сразу возвращалася с hashmap
        beanStorage.put(beanName, bean);

        return bean;
    }
}
