/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections.primitives;

import java.util.EmptyStackException;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests the ByteStack class.
 *
 * @author Apache Directory Project
 * @since Commons Primitives 1.1
 * @version $Revision: 1.2 $ $Date: 2004/07/12 16:33:47 $
 */
public class TestByteStack extends TestCase
{
    ByteStack stack = null ;
    
    
    /**
     * Runs the test. 
     * 
     * @param args nada
     */
    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestByteStack.class ) ;
    }

    public static TestSuite suite() {
        return new TestSuite(TestByteStack.class);
    }

    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp() ;
        stack = new ByteStack() ;
    }
    
    
    /**
     * Constructor for IntStackTest.
     * @param arg0
     */
    public TestByteStack( String arg0 )
    {
        super( arg0 ) ;
    }

    
    public void testEmpty()
    {
        assertTrue( "Newly created stacks should be empty", stack.empty() ) ;
        stack.push( ( byte ) 0 ) ;
        assertFalse( "Stack with item should not be empty", stack.empty() ) ;
        stack.pop() ;
        assertTrue( "Stack last int popped should be empty", stack.empty() ) ;
    }

    
    public void testPeek()
    {
        try
        {
            stack.peek() ;
            throw new AssertionError( 
                    "Peek should have thrown an EmptyStackException" ) ;
        }
        catch( EmptyStackException e )
        {
            assertNotNull( "EmptyStackException should not be null", e ) ;
        }
        
        for( int ii = 0; ii < 10; ii++ )
        {    
            stack.push( ( byte ) ii ) ;
            assertTrue( ii == stack.peek() ) ;
        }
    }

    
    public void testPop()
    {
        try
        {
            stack.pop() ;
            throw new AssertionError( 
                    "Pop should have thrown an EmptyStackException" ) ;
        }
        catch( EmptyStackException e )
        {
            assertNotNull( "EmptyStackException should not be null", e ) ;
        }
        
        for( int ii = 0; ii < 10; ii++ )
        {    
            stack.push( ( byte ) ii ) ;
            assertTrue( ii == stack.pop() ) ;
        }

        for( int ii = 0; ii < 10; ii++ )
        {    
            stack.push( ( byte ) ii ) ;
        }
        for( int ii = 10; ii < 0; ii-- )
        {    
            stack.push( ( byte ) ii ) ;
            assertTrue( ii == stack.pop() ) ;
        }
    }

    
    public void testPush()
    {
        stack.push( ( byte ) 0 ) ;
        stack.push( ( byte ) 0 ) ;
        assertFalse( stack.empty() ) ;
        assertTrue( 0 == stack.pop() ) ;
        assertTrue( 0 == stack.pop() ) ;
    }

    
    public void testSearch()
    {
        stack.push( ( byte ) 0 ) ;
        stack.push( ( byte ) 1 ) ;
        assertTrue( 2 == stack.search( ( byte ) 0 ) ) ;
        stack.push( ( byte ) 0 ) ;
        assertTrue( 1 == stack.search( ( byte ) 0 ) ) ;
        stack.push( ( byte ) 0 ) ;
        assertTrue( 3 == stack.search( ( byte ) 1 ) ) ;
        assertTrue( -1 == stack.search( ( byte ) 44 ) ) ;
    }
}
