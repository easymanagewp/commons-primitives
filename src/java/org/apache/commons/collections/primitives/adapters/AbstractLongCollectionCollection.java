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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections.primitives.LongCollection;

/**
 * @since Commons Primitives 1.0
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff 
 */
abstract class AbstractLongCollectionCollection implements Collection {
    
    public boolean add(Object element) {
        return getLongCollection().add(((Number)element).longValue());
    }

    public boolean addAll(Collection c) {
        return getLongCollection().addAll(CollectionLongCollection.wrap(c));
    }
        
    public void clear() {
        getLongCollection().clear();
    }

    public boolean contains(Object element) {
        return getLongCollection().contains(((Number)element).longValue());
    }
   
    
    public boolean containsAll(Collection c) {
        return getLongCollection().containsAll(CollectionLongCollection.wrap(c));
    }        
        
    public String toString() {
        return getLongCollection().toString();
    }
    
    public boolean isEmpty() {
        return getLongCollection().isEmpty();
    }
    
    /**
     * {@link LongIteratorIterator#wrap wraps} the 
     * {@link org.apache.commons.collections.primitives.LongIterator LongIterator}
     * returned by my underlying 
     * {@link LongCollection LongCollection}, 
     * if any.
     */
    public Iterator iterator() {
        return LongIteratorIterator.wrap(getLongCollection().iterator());
    }
     
    public boolean remove(Object element) {
        return getLongCollection().removeElement(((Number)element).longValue());
    }
    
    public boolean removeAll(Collection c) {
        return getLongCollection().removeAll(CollectionLongCollection.wrap(c));
    }
    
    public boolean retainAll(Collection c) {
        return getLongCollection().retainAll(CollectionLongCollection.wrap(c));
    }
    
    public int size() {
        return getLongCollection().size();
    }
    
    public Object[] toArray() {
        long[] a = getLongCollection().toArray();
        Object[] A = new Object[a.length];
        for(int i=0;i<a.length;i++) {
            A[i] = new Long(a[i]);
        }
        return A;
    }
    
    public Object[] toArray(Object[] A) {
        long[] a = getLongCollection().toArray();
        if(A.length < a.length) {
            A = (Object[])(Array.newInstance(A.getClass().getComponentType(), a.length));
        }
        for(int i=0;i<a.length;i++) {
            A[i] = new Long(a[i]);
        }
        if(A.length > a.length) {
            A[a.length] = null;
        }

        return A;
    }

    protected abstract LongCollection getLongCollection();            
}
