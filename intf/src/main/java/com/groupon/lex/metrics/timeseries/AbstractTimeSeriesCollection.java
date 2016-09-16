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
package com.groupon.lex.metrics.timeseries;

import java.util.Collection;
import java.util.Objects;
import lombok.NonNull;

/**
 * Helper base class, that implements equals and hashCode for TimeSeriesCollection.
 */
public abstract class AbstractTimeSeriesCollection implements TimeSeriesCollection {
    @Override
    public final int hashCode() {
        return hashCode(this);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TimeSeriesCollection)) {
            return false;
        }
        final TimeSeriesCollection other = (TimeSeriesCollection) obj;
        return equals(this, other);
    }

    @Override
    public abstract TimeSeriesCollection clone();

    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        if (className.isEmpty())
            className = "anonymous-AbstractTimeSeriesCollection";

        return className + "{" + getTimestamp() + "}";
    }

    public static int hashCode(@NonNull TimeSeriesCollection c) {
        return c.getTimestamp().hashCode();
    }

    public static boolean equals(@NonNull TimeSeriesCollection a, @NonNull TimeSeriesCollection b) {
        if (!Objects.equals(a.getTimestamp(), b.getTimestamp()))
            return false;
        final Collection<TimeSeriesValue> aTSData = a.getTSValues();
        final Collection<TimeSeriesValue> bTSData = b.getTSValues();
        return aTSData.containsAll(bTSData) && bTSData.containsAll(aTSData);
    }
}
