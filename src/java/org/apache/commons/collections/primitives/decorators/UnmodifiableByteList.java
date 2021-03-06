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
package org.apache.commons.collections.primitives.decorators;

import java.io.Serializable;

import org.apache.commons.collections.primitives.ByteList;

/**
 * 
 * @since Commons Primitives 1.0
 * @version $Revision$ $Date$
 * 
 * @author Rodney Waldhoff 
 */
public final class UnmodifiableByteList extends BaseUnmodifiableByteList implements Serializable {
    UnmodifiableByteList(ByteList list) {
        this.proxied = list;
    }
    
    public static final ByteList wrap(ByteList list) {
        if(null == list) {
            return null; 
        } else if(list instanceof UnmodifiableByteList) {
            return list;
        } else if(list instanceof Serializable) {
            return new UnmodifiableByteList(list);
        } else {
            return new NonSerializableUnmodifiableByteList(list);
        }
    }

    protected ByteList getProxiedList() {
        return proxied;
    }

    private ByteList proxied = null;
}
