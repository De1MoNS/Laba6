package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        // Задание 1
        System.out.println("Задание 1");
        int N = 10; // количество случайных чисел
        int[] randomNumbers = createRandomArray(N);
        System.out.println("Массив случайных чисел: " + Arrays.toString(randomNumbers));

        List<Integer> numberList = convertArrayToList(randomNumbers);
        System.out.println("Список из массива: " + numberList);

        Collections.sort(numberList);
        System.out.println("Отсортированный список (по возрастанию): " + numberList);

        Collections.reverse(numberList);
        System.out.println("Отсортированный список (обратный порядок): " + numberList);

        Collections.shuffle(numberList);
        System.out.println("Перемешанный список: " + numberList);

        // Циклический сдвиг на 1 элемент
        int firstElement = numberList.remove(0);
        numberList.add(firstElement);
        System.out.println("Список после циклического сдвига: " + numberList);

        List<Integer> uniqueList = retainUniqueElements(numberList);
        System.out.println("Уникальные элементы списка: " + uniqueList);

        List<Integer> duplicateList = retainDuplicateElements(numberList);
        System.out.println("Дублирующиеся элементы списка: " + duplicateList);

        Integer[] arrayFromList = listToArray(numberList);
        System.out.println("Массив из списка: " + Arrays.toString(arrayFromList));

        Map<Integer, Integer> occurrences = countOccurrences(arrayFromList);
        System.out.println("Количество вхождений каждого числа: " + occurrences);

        // Задание 2
        System.out.println("Задание 2");
        PrimesGenerator primesGenerator = new PrimesGenerator();
        List<Integer> primes = primesGenerator.getFirstPrimes(10);
        System.out.println("Первый 10 простых чисел: " + primes);
        System.out.println("Простые числа в обратном порядке: ");
        Iterator<Integer> iterator = primes.iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }

        // Задание 3
        System.out.println("Задание 3");
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Иван", "Иванов", 30));
        humans.add(new Human("Петр", "Петров", 25));
        humans.add(new Human("Александр", "Александров", 28));

        // a) HashSet
        Set<Human> hashSet = new HashSet<>(humans);
        System.out.println("HashSet: " + hashSet);

        // b) LinkedHashSet
        Set<Human> linkedHashSet = new LinkedHashSet<>(humans);
        System.out.println("LinkedHashSet: " + linkedHashSet);

        // c) TreeSet
        Set<Human> treeSet = new TreeSet<>(humans);
        System.out.println("TreeSet: " + treeSet);

        // d) TreeSet с компаратором
        Set<Human> customTreeSet = new TreeSet<>(new HumanComparatorByLastName());
        customTreeSet.addAll(humans);
        System.out.println("TreeSet с компаратором по фамилии: " + customTreeSet);

        // e) TreeSet с анонимным компаратором по возрасту
        Set<Human> ageTreeSet = new TreeSet<>(new Comparator<Human>()
        {
            public int compare(Human h1, Human h2)
            {
                return Integer.compare(h1.getAge(), h2.getAge());
            }
        });
        ageTreeSet.addAll(humans);
        System.out.println("TreeSet с анонимным компаратором по возрасту: " + ageTreeSet);

        // Задание 4
        System.out.println("Задание 4");
        String text = "Sentence with word frequency. Sentence with very high word frequency";
        countWordFrequency(text);

        // Задание 5
        System.out.println("Задание 5");
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("Первый", 1);
        originalMap.put("Второй", 2);
        originalMap.put("Третий", 3);

        Map<Integer, String> swappedMap = swapKeysAndValues(originalMap);
        System.out.println("С swappedMap: " + swappedMap);
    }

    // Задание 1a
    static int[] createRandomArray(int N)
    {
        Random random = new Random();
        return random.ints(N, 0, 101).toArray();
    }

    // Задание 1b
    static List<Integer> convertArrayToList(int[] array)
    {
        List<Integer> list = new ArrayList<>();
        for (int num : array)
        {
            list.add(num);
        }
        return list;
    }

    // Задание 1g
    static List<Integer> retainUniqueElements(List<Integer> list)
    {
        Set<Integer> uniqueSet = new HashSet<>(list);
        return new ArrayList<>(uniqueSet);
    }

    // Задание 1h
    static List<Integer> retainDuplicateElements(List<Integer> list)
    {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : list)
        {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        List<Integer> duplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet())
        {
            if (entry.getValue() > 1)
            {
                duplicates.add(entry.getKey());
            }
        }
        return duplicates;
    }

    // Задание 1i
    static Integer[] listToArray(List<Integer> list)
    {
        return list.toArray(new Integer[0]);
    }

    // Задание 1j
    static Map<Integer, Integer> countOccurrences(Integer[] array)
    {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (Integer num : array)
        {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        return occurrences;
    }

    // Задание 4
    static void countWordFrequency(String text)
    {
        String[] words = text.toLowerCase().split("\\W+");
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words)
        {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        System.out.println("Частота слов: " + frequencyMap);
    }

    // Задание 5
    static <K, V> Map<V, K> swapKeysAndValues(Map<K, V> originalMap)
    {
        Map<V, K> swappedMap = new HashMap<>();
        for (Map.Entry<K, V> entry : originalMap.entrySet())
        {
            swappedMap.put(entry.getValue(), entry.getKey());
        }
        return swappedMap;
    }
}

//Генерация простых чисел
class PrimesGenerator
{
    public List<Integer> getFirstPrimes(int count)
    {
        List<Integer> primes = new ArrayList<>();
        if (count > 0)
        {
            primes.add(2);
        }
        for (int i = 3; primes.size() < count; i += 2)
        {
            if (isPrime(i))
            {
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int n)
    {
        for (int i = 2; i <= Math.sqrt(n); i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }
}

// Класс человека
class Human implements Comparable<Human>
{
    private String name;
    private String surname;
    private int age;

    Human(String name, String surname, int age)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public int getAge()
    {
        return age;
    }

    public String HOut()
    {
        return "Имя: " + name + ", Фамилия: " + surname + ", Возраст: " + age;
    }

    public int compareTo(Human other)
    {
        return this.surname.compareTo(other.surname);
    }

    public String toString()
    {
        return HOut();
    }
}

//Компаратор для класса Human
class HumanComparatorByLastName implements Comparator<Human>
{
    public int compare(Human a, Human b)
    {
        return a.getSurname().compareTo(b.getSurname());
    }
}