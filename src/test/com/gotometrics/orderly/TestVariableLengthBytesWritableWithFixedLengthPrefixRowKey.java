/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package com.gotometrics.orderly;

import org.apache.hadoop.io.BytesWritable;

public class TestVariableLengthBytesWritableWithFixedLengthPrefixRowKey extends RandomRowKeyTestCase {

    @Override
    public RowKey createRowKey() {
        return new VariableLengthBytesWritableRowKey(r.nextInt(10));
    }

    @Override
    public Object createObject() {
        final int length = r.nextInt(1000) + 10;
        final byte[] randomBytes = new byte[length];
        r.nextBytes(randomBytes);
        return new BytesWritable(randomBytes);
    }

    @Override
    public int compareTo(Object o1, Object o2) {
        if (o1 == null || o2 == null)
            return (o1 != null ? 1 : 0) - (o2 != null ? 1 : 0);

        BytesWritable b1 = ((BytesWritable) o1);
        BytesWritable b2 = ((BytesWritable) o2);

        final int compareTo = b1.compareTo(b2);

        return compareTo < 0 ? -1 : compareTo > 0 ? 1 : 0;
    }

}
