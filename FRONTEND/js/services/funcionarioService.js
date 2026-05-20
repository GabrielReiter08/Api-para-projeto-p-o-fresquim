import { api } from "./api.js";

export const funcionarioService = {
  listar: () => api.get("/funcionarios"),

  buscarPorId: (id) =>
    api.get(`/funcionarios/${id}`),

  cadastrar: (funcionario) =>
    api.post("/funcionarios", funcionario),

  editar: (id, funcionario) =>
    api.put(`/funcionarios/${id}`, funcionario),

  deletar: (id) =>
    api.delete(`/funcionarios/${id}`)
};