/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections.primitives.adapters;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.collections.iterators.AbstractTestListIterator;
import org.apache.commons.collections.primitives.ArrayIntList;
import org.apache.commons.collections.primitives.IntList;

/**
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 */
public class TestIntListIteratorListIterator extends AbstractTestListIterator {

    // conventional
    // ------------------------------------------------------------------------

    public TestIntListIteratorListIterator(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestIntListIteratorListIterator.class);
    }

    // collections testing framework
    // ------------------------------------------------------------------------

    public ListIterator makeEmptyListIterator() {
        return IntListIteratorListIterator.wrap(makeEmptyIntList().listIterator());
    }
    
    public ListIterator makeFullListIterator() {
        return IntListIteratorListIterator.wrap(makeFullIntList().listIterator());
    }

    protected IntList makeEmptyIntList() {
        return new ArrayIntList();
    }
    
    protected IntList makeFullIntList() {
        IntList list = makeEmptyIntList();
        int[] elts = getFullElements();
        for(int i=0;i<elts.length;i++) {
            list.add(elts[i]);
        }
        return list;
    }
    
    public int[] getFullElements() {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }
    
    public Object addSetValue() {
        return new Integer(1);
    }

    // tests
    // ------------------------------------------------------------------------

    
    public void testNextHasNextRemove() {
        int[] elements = getFullElements();
        Iterator iter = makeFullIterator();
        for(int i=0;i<elements.length;i++) {
            assertTrue(iter.hasNext());
            assertEquals(new Integer(elements[i]),iter.next());
            if(supportsRemove()) {
                iter.remove();
            }
        }        
        assertTrue(! iter.hasNext() );
    }

    public void testEmptyIterator() {
        assertTrue( ! makeEmptyIterator().hasNext() );
        try {
            makeEmptyIterator().next();
            fail("Expected NoSuchElementException");
        } catch(NoSuchElementException e) {
            // expected
        }
        if(supportsRemove()) {
            try {
                makeEmptyIterator().remove();
                fail("Expected IllegalStateException");
            } catch(IllegalStateException e) {
                // expected
            }
        }        
    }

    public void testRemoveBeforeNext() {
        if(supportsRemove()) {
            try {
                makeFullIterator().remove();
                fail("Expected IllegalStateException");
            } catch(IllegalStateException e) {
                // expected
            }
        }        
    }

    public void testRemoveAfterRemove() {
        if(supportsRemove()) {
            Iterator iter = makeFullIterator();
            iter.next();
            iter.remove();
            try {
                iter.remove();
                fail("Expected IllegalStateException");
            } catch(IllegalStateException e) {
                // expected
            }
        }        
    }

}
