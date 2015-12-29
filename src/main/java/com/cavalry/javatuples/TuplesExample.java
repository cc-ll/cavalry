package com.cavalry.javatuples;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Unit;

/**
 * author: master
 * date: 2015/12/29
 */
public class TuplesExample {

    /*
     Unit<A> (1 element)
     Pair<A,B> (2 elements)
     Triplet<A,B,C> (3 elements)
     Quartet<A,B,C,D> (4 elements)
     Quintet<A,B,C,D,E> (5 elements)
     Sextet<A,B,C,D,E,F> (6 elements)
     Septet<A,B,C,D,E,F,G> (7 elements)
     Octet<A,B,C,D,E,F,G,H> (8 elements)
     Ennead<A,B,C,D,E,F,G,H,I> (9 elements)
     Decade<A,B,C,D,E,F,G,H,I,J> (10 elements)
     */

    public static void main(String[] args) {
        Unit<Integer> unit = Unit.with(1);
        Pair<Integer, String> pair = Pair.with(1, "TWO");
        Triplet<Integer, String, Integer> triplet = Triplet.with(1, "TWO", 3);

        System.out.println(unit.getValue0());
        System.out.println(pair.getValue0() + " " + pair.getValue1());
        System.out.println(triplet.getValue0() + " " + triplet.getValue1() + " " + triplet.getValue2());

        // http://www.javatuples.org/using.html
    }
}
