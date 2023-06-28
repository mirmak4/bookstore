/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.testutils;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author miron.maksymiuk
 */
public class TestUtils {
    
    public static Long getCrudRepoIterableSize(Iterable books) {
        return convertIterableToStream(books).count();
    }
    
    private static Stream convertIterableToStream(Iterable iter) {
        return StreamSupport.stream(iter.spliterator(), false);
    }
}
