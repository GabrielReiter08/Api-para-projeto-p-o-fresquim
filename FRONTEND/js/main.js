  import { produtoService } from "./services/produtoService.js";
  import { clienteService } from "./services/clienteService.js";
  import { funcionarioService } from "./services/funcionarioService.js";
  import { vendaService } from "./services/vendaService.js";
  
  let currentScreen = 'home';
  let scannerOrigin = 'venda';
  let selectedPayment = '';
  let currentChartTab = { grafico: 'dia', filtro: 'semana' };

  const chartData = {
    grafico: {
      dia: { labels:['6','7','8','9','10','11','12'], values:[80,120,250,200,180,160,90], highlight:2, date:'Hoje: 24/04/2024', total:'R$ 780', totalLabel:'Total vendido hoje:' },
      semana: { labels:['Seg','Ter','Qua','Qui','Sex','Sab','Dom'], values:[320,450,280,500,410,380,200], highlight:3, date:'Semana: 22-28/04/2024', total:'R$ 2.540', totalLabel:'Total na semana:' },
      mes: { labels:['S1','S2','S3','S4'], values:[980,1200,850,1150], highlight:1, date:'Mes: Abril 2024', total:'R$ 4.180', totalLabel:'Total no mes:' }
    },
    filtro: {
      queijo: { dia:[40,60,80,50,90,70,55], semana:[200,320,180,400,280,220,150], mes:[850,1200,980,1100] },
      frances: { dia:[90,120,200,160,140,110,80], semana:[500,620,480,700,560,440,310], mes:[1800,2200,1950,2100] },
      sovado: { dia:[30,50,70,40,60,50,35], semana:[150,240,130,300,200,180,120], mes:[650,900,720,850] },
      rosca: { dia:[20,40,60,35,50,45,28], semana:[120,200,110,260,180,150,90], mes:[520,750,580,700] }
    }
  };

  const filtroTotals = {
    queijo: { dia:'R$ 445', semana:'R$ 1.200', mes:'R$ 4.930' },
    frances: { dia:'R$ 900', semana:'R$ 2.610', mes:'R$ 8.050' },
    sovado: { dia:'R$ 335', semana:'R$ 850', mes:'R$ 2.720' },
    rosca: { dia:'R$ 278', semana:'R$ 810', mes:'R$ 2.550' }
  };

  const clientesData = {
    joao: { nome:'Joao Silva', tel:'(34) 99999-1111', end:'Rua das Flores, 123', status:'aprovado' },
    maria: { nome:'Maria Souza', tel:'(34) 99888-2222', end:'Av. Brasil, 456', status:'negativado' },
    pedro: { nome:'Pedro Oliveira', tel:'(34) 97777-3333', end:'Rua do Comercio, 78', status:'aprovado' }
  };

  const funcionariosData = {
    carlos: { nome:'Carlos', cargo:'Padeiro', admissao:'01/02/2024', contato:'(34) 09999-0000', saida:'12:00' },
    ana: { nome:'Ana', cargo:'Atendente', admissao:'15/03/2024', contato:'(34) 08888-0001', saida:'18:00' }
  };

  function goTo(screen, param) {
    if (param) {
      if (screen === 'cliente-detalhe') loadClienteDetalhe(param);
      if (screen === 'funcionario-detalhe') loadFuncDetalhe(param);
    }
    document.querySelectorAll('.screen').forEach(s => s.classList.remove('active'));
    const el = document.getElementById('screen-' + screen);
    if (el) { el.classList.add('active'); currentScreen = screen; }
    if (screen === 'grafico') renderChart('grafico', currentChartTab.grafico);
    if (screen === 'filtro-produto') renderFiltroChart('queijo', currentChartTab.filtro);
  }

  function openScanner(origin) {
    scannerOrigin = origin;
    goTo('scanner');
  }

  function loadClienteDetalhe(id) {
    const c = clientesData[id];
    if (!c) return;
    document.getElementById('cliente-detalhe-title').textContent = c.nome;
    document.getElementById('cliente-detalhe-nome').textContent = c.nome;
    document.getElementById('cliente-detalhe-tel').textContent = c.tel;
    document.getElementById('cliente-detalhe-end').textContent = c.end;
    const statusEl = document.getElementById('cliente-detalhe-status');
    if (c.status === 'aprovado') {
      statusEl.innerHTML = '<span class="badge badge-green"><svg width="10" height="10" viewBox="0 0 10 10"><circle cx="5" cy="5" r="4" fill="#2E7D32"/><path d="M3 5L4.5 6.5L7 3.5" stroke="white" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round" fill="none"/></svg>Aprovado</span>';
    } else {
      statusEl.innerHTML = '<span class="badge badge-red"><svg width="10" height="10" viewBox="0 0 10 10"><circle cx="5" cy="5" r="4" fill="#C62828"/><path d="M4 4L6 6M6 4L4 6" stroke="white" stroke-width="1.2" stroke-linecap="round"/></svg>Negativado</span>';
    }
  }

  function loadFuncDetalhe(id) {
    const f = funcionariosData[id];
    if (!f) return;
    document.getElementById('func-detalhe-title').textContent = f.nome;
    document.getElementById('func-nome').textContent = f.nome;
    document.getElementById('func-cargo').textContent = f.cargo;
    document.getElementById('func-admissao').textContent = f.admissao;
    document.getElementById('func-contato').textContent = f.contato;
    document.getElementById('func-saida').textContent = f.saida;
  }

  function selectPay(btn) {
    document.querySelectorAll('.btn-pay').forEach(b => b.classList.remove('selected'));
    btn.classList.add('selected');
    selectedPayment = btn.textContent;
  }

  function changeQty(btn, delta) {
    const row = btn.closest('.venda-item');
    const qtyEl = row.querySelector('.qty-display');
    const priceEl = row.querySelector('.venda-item-price');
    const unit = parseFloat(priceEl.dataset.unit);
    let qty = parseInt(qtyEl.textContent) + delta;
    if (qty < 1) qty = 1;
    qtyEl.textContent = qty;
    priceEl.textContent = 'R$ ' + (unit * qty).toFixed(2).replace('.',',');
    updateVendaTotal();
  }

  function removeItem(btn) {
    btn.closest('.venda-item').remove();
    updateVendaTotal();
  }

  function updateVendaTotal() {
    let total = 0;
    document.querySelectorAll('#venda-items-list .venda-item-price').forEach(el => {
      const val = el.textContent.replace('R$ ','').replace(',','.');
      total += parseFloat(val) || 0;
    });
    const disp = document.getElementById('venda-total-display').querySelector('.venda-total-value');
    disp.textContent = 'R$ ' + total.toFixed(2).replace('.',',');
    document.getElementById('modal-total').textContent = 'R$ ' + total.toFixed(2).replace('.',',');
  }

  function finalizarVenda() {
    if (!selectedPayment) { showNotif('Selecione uma forma de pagamento!', true); return; }
    document.getElementById('modal-venda').classList.add('open');
  }

  function closeModal(id) { document.getElementById(id).classList.remove('open'); }

  function resetVenda() {
    document.getElementById('venda-items-list').innerHTML = '';
    selectedPayment = '';
    document.querySelectorAll('.btn-pay').forEach(b => b.classList.remove('selected'));
    updateVendaTotal();
    goTo('venda');
  }

  function verificarSerasa() {
    const nome = document.getElementById('nc-nome').value;
    const result = document.getElementById('serasa-result');
    result.style.display = 'flex';
    if (nome.toLowerCase().includes('negativ') || Math.random() < 0.25) {
      result.className = 'serasa-status reprovado';
      result.innerHTML = '<svg width="16" height="16" viewBox="0 0 16 16" fill="none"><circle cx="8" cy="8" r="7" fill="#C62828"/><path d="M5.5 5.5L10.5 10.5M10.5 5.5L5.5 10.5" stroke="white" stroke-width="1.8" stroke-linecap="round"/></svg> Status: Reprovado - Negativado';
    } else {
      result.className = 'serasa-status';
      result.innerHTML = '<svg width="16" height="16" viewBox="0 0 16 16" fill="none"><circle cx="8" cy="8" r="7" fill="#2E7D32"/><path d="M5 8L7 10L11 6" stroke="white" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"/></svg> Status: Aprovado';
    }
  }

  async function salvarCliente() {
  const nome = document.getElementById("nc-nome").value;
  const telefone = document.getElementById("nc-tel").value;
  const endereco = document.getElementById("nc-end").value;

  if (!nome) {
    showNotif("Preencha o nome do cliente!", true);
    return;
  }

  const cliente = {
    nome,
    telefone,
    endereco
  };

  try {
    await clienteService.cadastrar(cliente);

    showNotif(`Cliente "${nome}" salvo com sucesso!`);

    document.getElementById("nc-nome").value = "";
    document.getElementById("nc-tel").value = "";
    document.getElementById("nc-end").value = "";
    document.getElementById("serasa-result").style.display = "none";

    setTimeout(() => goTo("clientes"), 1500);
  } catch (error) {
    console.error(error);
    showNotif("Erro ao salvar cliente!", true);
  }
}

  async function salvarFuncionario() {
  const nome = document.getElementById("nf-nome").value;

  if (!nome) {
    showNotif("Preencha o nome do funcionário!", true);
    return;
  }

  const funcionario = {
    nome
  };

  try {
    await funcionarioService.cadastrar(funcionario);

    showNotif(`Funcionário "${nome}" salvo!`);

    document.getElementById("nf-nome").value = "";

    setTimeout(() => goTo("funcionarios"), 1500);
  } catch (error) {
    console.error(error);
    showNotif("Erro ao salvar funcionário!", true);
  }
}
  async function salvarProduto() {
  const nome = document.getElementById("np-nome").value;
  const preco = document.getElementById("np-preco").value;
  const codigo = document.getElementById("np-cod").value;

  if (!nome || !preco) {
    showNotif("Preencha todos os campos!", true);
    return;
  }

  const produto = {
    nome,
    preco: Number(preco),
    categoria: "Padaria",
    codigo_barras: codigo
  };

  try {
    await produtoService.cadastrar(produto);

    showNotif(`Produto "${nome}" salvo!`);

    document.getElementById("np-nome").value = "";
    document.getElementById("np-preco").value = "";
    document.getElementById("np-cod").value = "";

    setTimeout(() => goTo("produtos"), 1500);
  } catch (error) {
    console.error(error);
    showNotif("Erro ao salvar produto!", true);
  }
}
  function simulateScan() {
    showNotif('Produto encontrado: Pao sovado');
    if (scannerOrigin === 'novo-produto') {
      setTimeout(() => {
        goTo('novo-produto');
        document.getElementById('np-cod').value = '1234567891012';
        document.getElementById('np-nome').value = 'Pao sovado';
      }, 1200);
    } else {
      setTimeout(() => {
        goTo('venda');
        const list = document.getElementById('venda-items-list');
        const item = document.createElement('div');
        item.className = 'venda-item';
        item.style.marginTop = '8px';
        item.innerHTML = '<div style="display:flex;align-items:center;gap:10px"><div><div class="venda-item-name">Pao sovado</div><div style="font-size:11px;color:var(--text-light);font-weight:600">R$ 6,50 / un</div></div></div><div style="display:flex;align-items:center;gap:8px"><button class="qty-btn" onclick="changeQty(this,-1)">-</button><span class="qty-display">1</span><button class="qty-btn" onclick="changeQty(this,1)">+</button><span class="venda-item-price" data-unit="6.50">R$ 6,50</span><button class="remove-btn" onclick="removeItem(this)">x</button></div>';
        list.appendChild(item);
        updateVendaTotal();
      }, 1200);
    }
  }

  function updateRelatorio() {
    const periodo = document.getElementById('rel-periodo').value;
    const totais = { hoje:'R$ 780', semana:'R$ 2.540', mes:'R$ 5.000', ano:'R$ 48.200' };
    const vendas = { hoje:'12', semana:'47', mes:'198', ano:'1.204' };
    const tickets = { hoje:'R$ 65', semana:'R$ 54', mes:'R$ 106', ano:'R$ 98' };
    const labels = { hoje:'Hoje, 24/04/2024', semana:'22 - 28 de Abril', mes:'Abril 2024', ano:'Ano 2024' };
    if (totais[periodo]) {
      document.getElementById('rel-total').textContent = totais[periodo];
      document.getElementById('rel-num-vendas').textContent = vendas[periodo];
      document.getElementById('rel-ticket').textContent = tickets[periodo];
      document.getElementById('rel-periodo-label').textContent = labels[periodo];
    }
  }

  function switchTab(btn, section, tab) {
    const parent = btn.closest('.tabs-row');
    parent.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    currentChartTab[section] = tab;
    if (section === 'grafico') renderChart('grafico', tab);
    if (section === 'filtro') {
      const prod = document.querySelector('#screen-filtro-produto select').value;
      renderFiltroChart(prod, tab);
    }
  }

  function renderChart(section, tab) {
    const data = chartData.grafico[tab];
    const container = document.getElementById('grafico-bars');
    document.getElementById('chart-date').textContent = data.date;
    document.getElementById('chart-total-label').textContent = data.totalLabel;
    document.getElementById('chart-total-value').textContent = data.total;
    const max = Math.max(...data.values);
    container.innerHTML = data.values.map((v, i) => {
      const h = Math.round((v / max) * 100);
      const hl = i === data.highlight ? ' highlight' : '';
      const formattedV = 'R$ ' + v;
      return `<div class="bar-wrap"><div class="bar${hl}" style="height:${h}%"><div class="bar-tooltip">${formattedV}</div></div><span class="bar-label">${data.labels[i]}</span></div>`;
    }).join('');
  }

  function updateFiltroChart(prod) {
    renderFiltroChart(prod, currentChartTab.filtro);
  }

  function renderFiltroChart(prod, tab) {
    const values = chartData.filtro[prod][tab];
    const labels = { dia:['D','S','T','Q','Q','S','S'], semana:['D','S','T','Q','Q','S','S'], mes:['S1','S2','S3','S4'] };
    const tabLabels = labels[tab] || labels.semana;
    const container = document.getElementById('filtro-bars');
    const max = Math.max(...values);
    container.innerHTML = values.map((v, i) => {
      const h = Math.round((v / max) * 90);
      const hl = v === max ? ' highlight' : '';
      const day = tabLabels[i] || i;
      const formattedDay = tab === 'semana' ? ['Dom','Seg','Ter','Qua','Qui','Sab','Dom'][i] : day;
      const formattedV = 'R$ ' + v;
      return `<div class="bar-wrap"><div class="bar${hl}" style="height:${h}%"><div class="bar-tooltip">${(tab==='semana'?formattedDay:day)} - ${formattedV}</div></div><span class="bar-label">${day}</span></div>`;
    }).join('');
    document.getElementById('filtro-total').textContent = filtroTotals[prod][tab];
  }

  function filterClientes(val) {
    const items = document.querySelectorAll('#clientes-list .list-item');
    items.forEach(item => {
      const name = item.querySelector('.list-item-name').textContent.toLowerCase();
      item.style.display = name.includes(val.toLowerCase()) ? '' : 'none';
    });
  }

  function showNotif(msg, isError) {
    const existing = document.querySelector('.notif');
    if (existing) existing.remove();
    const notif = document.createElement('div');
    notif.className = 'notif';
    notif.textContent = msg;
    if (isError) notif.style.background = '#C62828';
    document.body.appendChild(notif);
    setTimeout(() => notif.remove(), 2500);
  }

  // Init charts on load
  window.addEventListener('load', () => {
    renderChart('grafico', 'dia');
    renderFiltroChart('queijo', 'semana');
  });

window.goTo = goTo;
window.openScanner = openScanner;
window.selectPay = selectPay;
window.changeQty = changeQty;
window.removeItem = removeItem;
window.finalizarVenda = finalizarVenda;
window.closeModal = closeModal;
window.resetVenda = resetVenda;
window.verificarSerasa = verificarSerasa;
window.salvarCliente = salvarCliente;
window.salvarFuncionario = salvarFuncionario;
window.salvarProduto = salvarProduto;
window.simulateScan = simulateScan;
window.updateRelatorio = updateRelatorio;
window.switchTab = switchTab;
window.updateFiltroChart = updateFiltroChart;
window.filterClientes = filterClientes;