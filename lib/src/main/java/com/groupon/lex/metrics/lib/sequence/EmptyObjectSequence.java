/*
 * Copyright (c) 2016, Groupon, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of GROUPON nor the names of its contributors may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.groupon.lex.metrics.lib.sequence;

import static java.util.Collections.emptyIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import static java.util.Spliterators.emptySpliterator;
import java.util.stream.Stream;

public class EmptyObjectSequence<T> implements ObjectSequence<T> {
    @Override
    public boolean isSorted() { return true; }
    @Override
    public boolean isNonnull() { return true; }
    @Override
    public boolean isDistinct() { return true; }

    @Override
    public T get(int index) {
        throw new NoSuchElementException("index " + index + " out of bounds [0..0)");
    }

    @Override
    public Iterator<T> iterator() { return emptyIterator(); }
    @Override
    public Spliterator<T> spliterator() { return emptySpliterator(); }
    @Override
    public Stream<T> stream() { return Stream.empty(); }
    @Override
    public Stream<T> parallelStream() { return Stream.empty(); }

    @Override
    public int size() { return 0; }
    @Override
    public boolean isEmpty() { return true; }

    @Override
    public ObjectSequence<T> reverse() { return this; }
}
