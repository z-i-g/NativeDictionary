import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NativeDictionaryTest {
    NativeDictionary<Integer> dictionary = new NativeDictionary<>(17, Integer.class);
    String key1 = "key1";
    String key2 = "key2";

    @Test
    public void put_whenNewKey() {
        dictionary.put(key1, 1);
        assertEquals(1, dictionary.values[dictionary.hashFun(key1)]);
    }

    @Test
    public void put_whenExistingKey() {
        dictionary.put(key1, 1);
        dictionary.put(key1, 2);
        assertEquals(2, dictionary.values[dictionary.hashFun(key1)]);

        dictionary.put(key1, 3);
        assertEquals(3, dictionary.values[dictionary.hashFun(key1)]);
    }

    @Test
    public void isKey_whenNonExistentKey() {
        assertFalse(dictionary.isKey(key1));
    }

    @Test
    public void isKey_whenExistingKey() {
        String key3 = new String("key1");
        dictionary.put(key1, 1);
        dictionary.put(key2, 2);
        assertTrue(dictionary.isKey(key1));
        assertTrue(dictionary.isKey(key2));
        assertTrue(dictionary.isKey(key3));
    }

    @Test
    public void get_whenNonExistentKey() {
        assertNull(dictionary.get(key1));
    }

    @Test
    public void get_whenExistingKey() {
        dictionary.put(key1, 1);
        dictionary.put(key2, 2);

        assertEquals(1, dictionary.get(key1));
        assertEquals(2, dictionary.get(key2));
    }
}