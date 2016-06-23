package com.groupon.lex.metrics.history;

import com.groupon.lex.metrics.timeseries.TimeSeriesCollection;
import java.util.Collection;
import static java.util.Objects.requireNonNull;
import java.util.stream.Stream;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 *
 * @author ariane
 */
public abstract class AbstractCollectHistory<TSD_Impl extends TSData> implements CollectHistory {
    private final TSD_Impl historical_;

    protected AbstractCollectHistory(TSD_Impl initial) {
        historical_ = requireNonNull(initial);
    }

    @Override
    public DateTime getEnd() {
        return historical_.getEnd();
    }

    protected final TSD_Impl getTSData() { return historical_; }

    @Override
    public Stream<TimeSeriesCollection> stream() {
        return getTSData().stream();
    }
    @Override
    public Stream<TimeSeriesCollection> streamReversed() {
        return getTSData().streamReversed();
    }
    @Override
    public Stream<TimeSeriesCollection> stream(Duration stepsize) {
        return getTSData().stream(stepsize);
    }
    @Override
    public Stream<TimeSeriesCollection> stream(DateTime begin) {
        return getTSData().stream(begin);
    }
    @Override
    public Stream<TimeSeriesCollection> stream(DateTime begin, Duration stepsize) {
        return getTSData().stream(begin, stepsize);
    }
    @Override
    public Stream<TimeSeriesCollection> stream(DateTime begin, DateTime end) {
        return getTSData().stream(begin, end);
    }
    @Override
    public Stream<TimeSeriesCollection> stream(DateTime begin, DateTime end, Duration stepsize) {
        return getTSData().stream(begin, end, stepsize);
    }

    @Override
    public boolean add(TimeSeriesCollection tsv) {
        return getTSData().add(tsv);
    }

    @Override
    public boolean addAll(Collection<? extends TimeSeriesCollection> c) {
        return getTSData().addAll(c);
    }

    @Override
    public long getFileSize() {
        return getTSData().getFileSize();
    }
}
