/**
 * Copyright 2011-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.graph.util;

import org.springframework.util.Assert;

/**
 * <p>
 * Thread safe counter.
 * </p>
 */
public class Counter implements Comparable<Counter> {

    private int count;

    public Counter() {
        this(0);
    }

    public Counter(int count) {
        super();
        this.count = count;
    }

    public synchronized int increment() {
        Assert.state(count < Integer.MAX_VALUE, "Maximum counter value exceeded");
        return count++;
    }

    public synchronized int decrement() {
        Assert.state(count > Integer.MIN_VALUE, "Minimum counter value exceeded");
        return count--;
    }

    public synchronized int getCount() {
        return count;
    }

    @Override
    public synchronized int compareTo(Counter other) {
        return count > other.count ? 1 : count < other.count ? -1 : 0;
    }

}
