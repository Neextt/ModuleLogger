package io.github.biielkts.logger.logger.writers;

import io.github.biielkts.logger.logger.BukkitPlugin;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedHashMap;
import java.util.Map;


public class WriterUtils {

    private WriterUtils logger;
    private File file;
    private String header;
    private Map<String, Object> keys = new LinkedHashMap<>();

    public WriterUtils(BukkitPlugin logger, File file) {
        this(logger, file, "");
    }

    public WriterUtils(BukkitPlugin logger, File file, String header) {
        this.file = file;
        this.header = header;
    }
    public static class YamlEntry {

        private String annotation;
        private Object value;

        public YamlEntry(Object[] array) {
            this.annotation = (String) array[0];
            this.value = array[1];
        }

        public String getAnnotation() {
            return annotation;
        }

        public Object getValue() {
            return value;
        }
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface YamlEntryInfo {
        String annotation() default "";
    }
}
