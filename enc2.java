// В данном примере Vehicle является базовым классом, представляющим общие характеристики для всех транспортных средств. Car является подклассом Vehicle и наследует его свойства и методы. 
// Кроме того, Car добавляет свой собственный метод honk и переопределяет метод start.

// Преимущества наследования:

// Повторное использование кода: Классы-наследники могут использовать свойства и методы своих родителей, что уменьшает дублирование кода.
// Иерархия классов: Наследование позволяет строить иерархию классов, что упрощает организацию и понимание кода.
// Расширяемость: Можно легко добавлять новые функции в дочерние классы, расширяя функциональность базового класса.

// Недостатки наследования:

// Сложность: Иногда иерархии классов могут стать сложными, особенно при неудачном проектировании, что затрудняет понимание кода.
// Жесткость: Наследование создает жесткую связь между родительским и дочерним классами, что может усложнить изменения в иерархии классов.
// Проблемы с поддержкой: Некорректное использование наследования может привести к проблемам с поддержкой и обновлением кода.

// Базовый класс 
class Vehicle {
    private String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void start() {
        System.out.println("The vehicle is starting.");
    }

    public void stop() {
        System.out.println("The vehicle is stopping.");
    }

    public String getBrand() {
        return brand;
    }
}

// Подкласс, наследующий от Vehicle
class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String brand, int numberOfDoors) {
        super(brand); // Вызов конструктора родительского класса
        this.numberOfDoors = numberOfDoors;
    }

    // Дополнительный метод для подкласса Car
    public void honk() {
        System.out.println("Beep beep!");
    }

    // Переопределение метода start из родительского класса
    @Override
    public void start() {
        System.out.println("The car's engine is starting.");
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        // Использование наследования
        Car myCar = new Car("Toyota", 4);
        System.out.println("Brand: " + myCar.getBrand());
        System.out.println("Number of doors: " + myCar.numberOfDoors);

        // Вызов методов как из родительского, так и из дочернего класса
        myCar.start(); // Переопределенный метод
        myCar.stop();  // Унаследованный метод
        myCar.honk();  // Метод из дочернего класса
    }
}
