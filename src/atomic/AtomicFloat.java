package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Altair on 2017/06/11.
 */
public class AtomicFloat extends Number {
    private AtomicInteger bits;

    public AtomicFloat() {
        this(0f);
    }

    public AtomicFloat(float initialValue) {
        bits = new AtomicInteger(Float.floatToIntBits(initialValue));
    }

    public final float get() {
        return Float.intBitsToFloat(bits.get());
    }

    @Override
    public final float floatValue() {
        return get();
    }

    @Override
    public final double doubleValue() {
        return (double)floatValue();
    }

    @Override
    public final int intValue() {
        return (int)floatValue();
    }

    @Override
    public final long longValue() {
        return (long)floatValue();
    }

    public final String toString() {
        return Float.toString(get());
    }

    public final void set(float newValue) {
        bits.set(Float.floatToIntBits(newValue));
    }

    public final boolean compareAndSet(float expect, float update) {
        return bits.compareAndSet(Float.floatToIntBits(expect), Float.floatToIntBits(update));
    }

    public final float addAndGet(float delta) {
        float expect;
        float update;

        do {
            expect = get();
            update = expect + delta;
        } while (!compareAndSet(expect, update));

        return update;
    }

    public final float getAndAdd(float delta) {
        float expect;
        float update;

        do {
            expect = get();
            update = expect + delta;
        } while (!compareAndSet(expect, update));

        return expect;
    }

    public final float getAndDecrement() {
        return getAndAdd(-1f);
    }

    public final float decrementAndGet() {
        return addAndGet(-1f);
    }

    public final float getAndIncrement() {
        return getAndAdd(1f);
    }

    public final float incrementAndGet() {
        return addAndGet(1f);
    }

    public final float getAndSet(float newValue) {
        float expect;
        do {
            expect = get();
        } while (compareAndSet(expect, newValue));

        return expect;
    }
}
