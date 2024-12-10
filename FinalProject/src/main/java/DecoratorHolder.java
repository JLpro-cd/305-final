
/**
 * Manages a Node's (class') Decorators in an ordered array, with each Decorator at a specific index.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class DecoratorHolder {

    private Decorator[] decorators = new Decorator[9]; // Ordered by what code generation would naturally print first (i.e. observable -> extends, which is first).
    private static final int SINGLETON_INDEX= 0;
    private static final int OBSERVABLE_INDEX = 1;
    private static final int DECORATABLE_INDEX = 2;
    private static final int DECORATION_INDEX = 3;
    private static final int OBSERVER_INDEX = 4;
    private static final int STRATEGY_INDEX = 5;
    private static final int CHAIN_MEMBER_INDEX = 6;
    private static final int FACTORY_INDEX = 7;
    private static final int PRODUCT_INDEX = 8;

    private int getDecoratorIndex(Decorator decorator) {
        if (decorator instanceof ConcreteObservableDecorator) {
            return OBSERVABLE_INDEX;
        } else if (decorator instanceof ConcreteChainMemberDecorator) {
            return CHAIN_MEMBER_INDEX;
        } else if (decorator instanceof ConcreteDecoratableDecorator) {
            return DECORATABLE_INDEX;
        } else if (decorator instanceof ConcreteDecorationDecorator) {
            return DECORATION_INDEX;
        } else if (decorator instanceof ConcreteObserverDecorator) {
            return OBSERVER_INDEX;
        } else if (decorator instanceof ConcreteStrategyDecorator) {
            return STRATEGY_INDEX;
        } else if (decorator instanceof ConcreteSingletonDecorator) {
            return SINGLETON_INDEX;
        } else if (decorator instanceof ConcreteFactoryDecorator) {
            return FACTORY_INDEX;
        } else {
            return PRODUCT_INDEX;
        }
    }

    /**
     * Adds a Decorator to its specific index in the array.
     */

    public void addDecorator(Decorator decorator) {
        int decoratorIndex = getDecoratorIndex(decorator);
        decorators[decoratorIndex] = decorator;
    }

    /**
     * Returns the array of Decorators.
     */

    public Decorator[] getDecorators() {
        return decorators;
    }




}
