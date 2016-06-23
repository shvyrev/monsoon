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
package com.groupon.lex.metrics.timeseries.expression;

import com.groupon.lex.metrics.Histogram;
import com.groupon.lex.metrics.MetricMatcher;
import com.groupon.lex.metrics.MetricValue;
import com.groupon.lex.metrics.lib.Any2;
import com.groupon.lex.metrics.timeseries.TagAggregationClause;
import com.groupon.lex.metrics.timeseries.TimeSeriesMetricAggregate;
import com.groupon.lex.metrics.timeseries.TimeSeriesMetricExpression;
import static com.groupon.lex.metrics.timeseries.expression.Priorities.BRACKETS;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author ariane
 */
public class CountExpression extends TimeSeriesMetricAggregate<Number> {
    public CountExpression(Collection<Any2<MetricMatcher, TimeSeriesMetricExpression>> matchers, TagAggregationClause aggregation) {
        super("count", matchers, aggregation);
    }

    @Override
    protected Long initial_() { return 0L; }
    @Override
    protected Number map_(MetricValue x) {
        final Optional<Number> hist_count = x.histogram().map(Histogram::getEventCount);
        return hist_count.orElse(x.isPresent() ? 1L : 0L);
    }
    @Override
    protected MetricValue unmap_(Number v) { return MetricValue.fromNumberValue(v); }
    @Override
    protected Number reducer_(Number x, Number y) {
        if (x instanceof Double || y instanceof Double)
            return x.doubleValue() + y.doubleValue();
        else
            return x.longValue() + y.longValue();
    }
    @Override
    protected MetricValue scalar_fallback_() { return MetricValue.fromIntValue(0); }

    @Override
    public int getPriority() {
        return BRACKETS;
    }
}
