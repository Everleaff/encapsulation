import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    private List<Card> cards;

    // Создание колоды карт
    public DeckOfCards() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    // Инициализация колоды карт
    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Перетасовка колоды
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Сдача одной карты
    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Колода пуста");
        }
        return cards.remove(0);
    }

    // Возвращение карты в колоду с контролем дублирования
    public void returnCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
            Collections.shuffle(cards);
        } else {
            System.out.println("Карта уже присутствует в колоде.");
        }
    }

    // Вывод содержимого колоды (для тестирования)
    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        System.out.println("Первая карта после тасовки: " + deck.dealCard());

        // Пример сдачи карты и ее возвращения с контролем дублирования
        Card cardToDeal = deck.dealCard();
        System.out.println("Сдана карта: " + cardToDeal);

        deck.returnCard(cardToDeal); // Возвращение карты в колоду
        System.out.println("Колода после возвращения карты:");
        deck.printDeck();
    }
}

// Перечисление мастей карт
enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

// Перечисление достоинств карт
enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

// Класс, представляющий карту
class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
