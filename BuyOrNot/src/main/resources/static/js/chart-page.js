let chart, candleSeries, volumeSeries;

function initChart() {
    const el = document.getElementById('chart');
    el.innerHTML = '';

    // Velog: createChart(container, options)
    chart = LightweightCharts.createChart(el, {
        width: el.clientWidth,
        height: 520,
        timeScale: { timeVisible: true, secondsVisible: false },
    });

    // Velog: addSeries(CandlestickSeries, 옵션)
    candleSeries = chart.addSeries(LightweightCharts.CandlestickSeries, {
        priceLineVisible: true,
    });

    // (선택) 거래량: HistogramSeries
    volumeSeries = chart.addSeries(LightweightCharts.HistogramSeries, {
        priceFormat: { type: 'volume' },
        base: 0,
    });

    // Velog: resize 대응
    const handleResize = () => chart.applyOptions({ width: el.clientWidth });
    window.addEventListener('resize', handleResize);

    // 페이지 이탈/리로드 시 정리(Velog: chart.remove)
    window.addEventListener('beforeunload', () => {
        window.removeEventListener('resize', handleResize);
        chart.remove();
    });
}

async function fetchCandles(stockId, resolution) {
    const url = `/api/charts/candles?stockId=${encodeURIComponent(stockId)}&resolution=${encodeURIComponent(resolution)}`;
    const res = await fetch(url);
    if (!res.ok) throw new Error(await res.text());
    return res.json();
}

function renderCandles(payload) {
    // payload.candles: [{time, open, high, low, close, volume}]
    candleSeries.setData(payload.candles.map(c => ({
        time: c.time, open: c.open, high: c.high, low: c.low, close: c.close
    })));

    // 볼륨(선택)
    volumeSeries.setData(payload.candles.map(c => ({
        time: c.time,
        value: c.volume ?? 0,
    })));
}

async function pollLatest(stockId, resolution) {
    const url = `/api/charts/latest-bar?stockId=${encodeURIComponent(stockId)}&resolution=${encodeURIComponent(resolution)}`;
    const res = await fetch(url);
    if (!res.ok) return;

    const c = await res.json();
    candleSeries.update({ time: c.time, open: c.open, high: c.high, low: c.low, close: c.close });
    volumeSeries.update({ time: c.time, value: c.volume ?? 0 });
}

document.addEventListener('DOMContentLoaded', async () => {
    initChart();

    const stockIdEl = document.getElementById('stockId');
    const resEl = document.getElementById('resolution');
    const btn = document.getElementById('loadBtn');

    const load = async () => {
        const payload = await fetchCandles(stockIdEl.value.trim(), resEl.value.trim());
        renderCandles(payload);
    };

    btn.addEventListener('click', load);
    await load();

    // 10초마다 업데이트(너 프로젝트 요구사항)
    setInterval(() => pollLatest(stockIdEl.value.trim(), resEl.value.trim()), 10000);
});
