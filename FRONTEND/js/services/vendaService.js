import { api } from "./api.js";

export const vendaService = {
  listar: () => api.get("/vendas"),

  buscarPorId: (id) =>
    api.get(`/vendas/${id}`),

  cadastrar: (venda) =>
    api.post("/vendas", venda),

  editar: (id, venda) =>
    api.put(`/vendas/${id}`, venda),

  deletar: (id) =>
    api.delete(`/vendas/${id}`)
};