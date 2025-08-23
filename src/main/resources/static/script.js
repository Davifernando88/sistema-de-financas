const API_URL = 'http://localhost:8080/financas';

async function adicionarReceita() {
    const descricao = document.getElementById('descricaoReceita').value;
    const valor = parseFloat(document.getElementById('valorReceita').value);
    const data = document.getElementById('dataReceita').value;

    const response = await fetch(`${API_URL}/receita`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ descricao, valor, data })
    });

    if (response.ok) {
        alert('Receita adicionada!');
        document.getElementById('descricaoReceita').value = '';
        document.getElementById('valorReceita').value = '';
        document.getElementById('dataReceita').value = '';
    }
}

async function adicionarDespesa() {
    const descricao = document.getElementById('descricaoDespesa').value;
    const valor = parseFloat(document.getElementById('valorDespesa').value);
    const data = document.getElementById('dataDespesa').value;

    const response = await fetch(`${API_URL}/despesa`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ descricao, valor, data })
    });

    if (response.ok) {
        alert('Despesa adicionada!');
        document.getElementById('descricaoDespesa').value = '';
        document.getElementById('valorDespesa').value = '';
        document.getElementById('dataDespesa').value = '';
    }
}

async function consultarSaldo() {
    const inicio = document.getElementById('inicioSaldo').value;
    const fim = document.getElementById('fimSaldo').value;

    const response = await fetch(`${API_URL}/saldo?inicio=${inicio}&fim=${fim}`);
    const saldo = await response.json();
    document.getElementById('resultadoSaldo').innerText = `Saldo: R$ ${saldo.toFixed(2)}`;
}

async function listarMovimentos() {
    const inicio = document.getElementById('inicioListagem').value;
    const fim = document.getElementById('fimListagem').value;

    const receitas = await fetch(`${API_URL}/receitas?inicio=${inicio}&fim=${fim}`).then(res => res.json());
    const despesas = await fetch(`${API_URL}/despesas?inicio=${inicio}&fim=${fim}`).then(res => res.json());

    let html = '<h3>Receitas:</h3><ul>';
    receitas.forEach(r => html += `<li>${r.data}: ${r.descricao} - R$ ${r.valor.toFixed(2)}</li>`);
    html += '</ul>';

    html += '<h3>Despesas:</h3><ul>';
    despesas.forEach(d => html += `<li>${d.data}: ${d.descricao} - R$ ${d.valor.toFixed(2)}</li>`);
    html += '</ul>';

    document.getElementById('listaMovimentos').innerHTML = html;
}
