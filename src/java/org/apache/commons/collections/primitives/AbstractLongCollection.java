/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//primitives/src/java/org/apache/commons/collections/primitives/AbstractLongCollection.java,v 1.1 2003/10/13 22:46:51 scolebourne Exp $
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgement may appear in the software itself,
 *    if and wherever such third-party acknowledgements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.collections.primitives;

/**
 * Abstract base class for {@link LongCollection}s.
 * <p />
 * Read-only subclasses must override {@link #iterator}
 * and {@link #size}.  Mutable subclasses
 * should also override {@link #add} and 
 * {@link LongIterator#remove LongIterator.remove}.
 * All other methods have at least some base implementation 
 * derived from these.  Subclasses may choose to override 
 * these methods to provide a more efficient implementation. 
 * 
 * @since Commons Collections 2.2
 * @version $Revision: 1.1 $ $Date: 2003/10/13 22:46:51 $
 * 
 * @author Rodney Waldhoff 
 */
public abstract class AbstractLongCollection implements LongCollection {
    public abstract LongIterator iterator();
    public abstract int size();
          
    protected AbstractLongCollection() { }
              
    /** Unsupported in this base implementation. */
    public boolean add(long element) {
        throw new UnsupportedOperationException("add(long) is not supported.");
    }

    public boolean addAll(LongCollection c) {
        boolean modified = false;
        for(LongIterator iter = c.iterator(); iter.hasNext(); ) {
            modified  |= add(iter.next());
        }
        return modified;
    }
    
    public void clear() {
        for(LongIterator iter = iterator(); iter.hasNext();) {
            iter.next();
            iter.remove();
        }
    }        

    public boolean contains(long element) {
        for(LongIterator iter = iterator(); iter.hasNext();) {
            if(iter.next() == element) {
                return true;
            }
        }
        return false;
    }
        
    public boolean containsAll(LongCollection c) {
        for(LongIterator iter = c.iterator(); iter.hasNext();) {
            if(!contains(iter.next())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isEmpty() {
        return (0 == size());
    }
       
    public boolean removeElement(long element) {
        for(LongIterator iter = iterator(); iter.hasNext();) {
            if(iter.next() == element) {
                iter.remove();
                return true;
            }
        }
        return false;
    }        
    
    public boolean removeAll(LongCollection c) {
        boolean modified = false;
        for(LongIterator iter = c.iterator(); iter.hasNext(); ) {
            modified  |= removeElement(iter.next());
        }
        return modified;
    }       
    
    public boolean retainAll(LongCollection c) {
        boolean modified = false;
        for(LongIterator iter = iterator(); iter.hasNext();) {
            if(!c.contains(iter.next())) {
                iter.remove();
                modified = true;
            }
        }
        return modified;
    }
    
    public long[] toArray() {
        long[] array = new long[size()];
        int i = 0;
        for(LongIterator iter = iterator(); iter.hasNext();) {
            array[i] = iter.next();
            i++;
        }
        return array;
    }
        
    public long[] toArray(long[] a) {
        if(a.length < size()) {
            return toArray();
        } else {
            int i = 0;
            for(LongIterator iter = iterator(); iter.hasNext();) {
                a[i] = iter.next();
                i++;
            }
            return a;
        }            
    }
}
