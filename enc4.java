// В этом примере Car содержит объект Engine, и его жизненный цикл полностью контролируется объектом Car.
// Если мы создаем новый объект Car, он автоматически создает новый объект Engine. Когда объект Car уничтожается, уничтожается и его Engine. Это иллюстрирует композицию.
// С другой стороны, в примере агрегации Team содержит объекты Player, но их жизненный цикл не зависит от жизненного цикла Team. 
// Мы можем добавлять и удалять игроков из команды, и их жизненный цикл не изменится. Это иллюстрирует агрегацию.

// Отличия между композицией и агрегацией:

// Жизненный цикл: В композиции объекты живут и умирают вместе (один объект является частью другого). В агрегации объекты могут существовать независимо друг от друга.
// Ответственность за создание: В композиции объект-часть обычно создается внутри объекта-контейнера. В агрегации объекты могут быть созданы отдельно и затем добавлены к контейнеру.
// Сложность отношений: Композиция обычно представляет собой более сильное и тесное отношение, в то время как агрегация более слабое и более свободное.

// Пример композиции
class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine();
    }

    public void start() {
        engine.start();
        System.out.println("Car started");
    }
}

// Пример агрегации
class Team {
    private List<Player> players;

    public Team() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void displayTeam() {
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }
}

// Класс, представляющий игрока
class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class CompositionVsAggregation {
    public static void main(String[] args) {
        // Пример композиции
        Car myCar = new Car();
        myCar.start();

        // Пример агрегации
        Team footballTeam = new Team();
        footballTeam.addPlayer(new Player("John"));
        footballTeam.addPlayer(new Player("Alice"));

        footballTeam.displayTeam();
    }
}
