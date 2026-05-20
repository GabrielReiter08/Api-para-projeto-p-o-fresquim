import { api } from "./api.js";

export const produtoService = {
  listar: () => api.get("/produtos"),

  buscarPorId: (id) =>
    api.get(`/produtos/${id}`),

  cadastrar: (produto) =>
    api.post("/produtos", produto),

  editar: (id, produto) =>
    api.put(`/produtos/${id}`, produto),

  deletar: (id) =>
    api.delete(`/produtos/${id}`)
};