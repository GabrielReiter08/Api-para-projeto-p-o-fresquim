import { api } from "./api.js";

export const clienteService = {
  listar: () => api.get("/clientes"),

  buscarPorId: (id) =>
    api.get(`/clientes/${id}`),

  cadastrar: (cliente) =>
    api.post("/clientes", cliente),

  editar: (id, cliente) =>
    api.put(`/clientes/${id}`, cliente),

  deletar: (id) =>
    api.delete(`/clientes/${id}`)
};