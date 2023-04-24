import java.lang.reflect.Array;

class NativeDictionary<T>
{
    public int size;
    public String [] slots;
    public T [] values;

    public NativeDictionary(int sz, Class clazz)
    {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key)
    {
        int intVal = 0;
        for (byte b: key.getBytes()) {
            intVal = intVal + b;
        }
        return Math.abs(intVal % size);
        // всегда возвращает корректный индекс слота
    }

    public boolean isKey(String key)
    {
        int idx = hashFun(key);
        return slots[idx] != null;
        // возвращает true если ключ имеется,
        // иначе false
    }

    public void put(String key, T value)
    {
        int idx = hashFun(key);
        slots[idx] = key;
        values[idx] = value;
        // гарантированно записываем
        // значение value по ключу key
    }

    public T get(String key)
    {
        int idx = hashFun(key);
        if (slots[idx] != null) {
            return values[idx];
        }
        return null;
        // возвращает value для key,
        // или null если ключ не найден
    }
}
