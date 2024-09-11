import java.util.ArrayList;
import java.util.List;

public class MyStringBuilder {
    private char[] value; // массив символов
    private int size; // текущий размер строки
    private List<String> snapshots; // для хранения состояний

    // Конструктор
    public MyStringBuilder() {
        value = new char[16]; // начальный размер
        size = 0;
        snapshots = new ArrayList<>();
    }

    // Метод, который добавляет строку
    public MyStringBuilder append(String str) {
        if (str == null) {
            return this; // Игнорируем null
        }
        saveSnapshot(); // сохраняем текущее состояние
        ensureCapacity(size + str.length()); // в случае необходимости увеличиваем размер
        for (char c : str.toCharArray()) {
            value[size++] = c; // Добавляем символы к массиву
        }
        return this;
    }

    // Метод для удаления часть строки
    public MyStringBuilder delete(int start, int end) {
        if (start < 0 || start >= size || end > size || start >= end) {
            throw new IndexOutOfBoundsException("Invalid start or end index");
        }
        saveSnapshot(); // сохраняем текущее состояние
        int deleteLength = end - start;
        System.arraycopy(value, end, value, start, size - end);
        size -= deleteLength; // уменьшаем размер
        return this;
    }

    // Метод для получения строки
    public String toString() {
        return new String(value, 0, size);
    }

    // Метод для отмены последнего изменения
    public void undo() {
        if (!snapshots.isEmpty()) {
            String lastSnapshot = snapshots.remove(snapshots.size() - 1); // извлечь последний снимок
            value = lastSnapshot.toCharArray(); // восстановить массив символов
            size = lastSnapshot.length(); // обновить размер
        }
    }

    // Вспомогательный метод для сохранения текущего состояния
    private void saveSnapshot() {
        snapshots.add(new String(value, 0, size)); // сохраняем текущее состояние
    }

    // Вспомогательный метод для увеличения размера массива
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > value.length) {
            int newCapacity = Math.max(value.length * 2, minCapacity); // удваиваем размер или устанавливаем минимальный
            char[] newValue = new char[newCapacity];
            System.arraycopy(value, 0, newValue, 0, size); // копируем старые данные
            value = newValue;
        }
    }
}
