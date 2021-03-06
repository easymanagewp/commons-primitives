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
package org.apache.commons.collections.primitives.adapters.io;

import java.io.InputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.collections.primitives.ArrayByteList;
import org.apache.commons.collections.primitives.ByteList;

/**
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 */
public class TestByteIteratorInputStream extends TestCase {

    // conventional
    // ------------------------------------------------------------------------

    public TestByteIteratorInputStream(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestByteIteratorInputStream.class);
    }

    // ------------------------------------------------------------------------
    


    // ------------------------------------------------------------------------
    
    public void testReadNonEmpty() throws Exception {
        ByteList list = new ArrayByteList();
        for(int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            list.add((byte)i);
        }
       
        InputStream in = new ByteIteratorInputStream(list.iterator());
        for(int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            assertEquals(0xFF&i,in.read());
        }
        assertEquals(-1,in.read());
        assertEquals(-1,in.read());
    }

    public void testReadEmpty() throws Exception {
        ByteList list = new ArrayByteList();
        InputStream in = new ByteIteratorInputStream(list.iterator());
        assertEquals(-1,in.read());
        assertEquals(-1,in.read());
    }

    public void testAdaptNull() {
        assertNull(ByteIteratorInputStream.adapt(null));
    }

    public void testAdaptNonNull() {
        assertNotNull(ByteIteratorInputStream.adapt(new ArrayByteList().iterator()));
    }
    
}
