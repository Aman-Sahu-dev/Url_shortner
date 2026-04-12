const upd = async () => {
    const data = await (await fetch('/urlshortner/all')).json();
    document.getElementById('t').innerHTML = data.map(i => `
        <tr>
            <td><div style="max-width:220px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap" title="${i.originalCode}">${i.originalCode}</div></td>
            <td><a class="mono" href="/urlshortner/${i.shortCode}" target="_blank">/${i.shortCode}</a></td>
            <td><span class="count">${i.count}</span></td>
        </tr>
    `).reverse().join('');
};

const s = async () => {
    const val = document.getElementById('u').value;
    if(!val) return alert('Please enter a URL');
    const res = await fetch('/urlshortner', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ originalUrl: val })
    });
    const d = await res.json();
    const link = window.location.origin + `/urlshortner/${d.shortCode}`;
    document.getElementById('res').style.display = 'block';
    document.getElementById('res').innerHTML = `Mapping generated!<br><div style="font-size:0.75rem;color:#8b949e;margin:4px 0">${val}</div>↳ <a href="${link}" target="_blank">${link}</a>`;
    upd(); 
};

upd();
