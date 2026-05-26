const API_URL = "https://api-para-projeto-p-o-fresquim.onrender.com";

async function request(endpoint, options = {}) {
  try {

    const response = await fetch(
      `${API_URL}${endpoint}`,
      {
        headers: {
          "Content-Type": "application/json",
          ...options.headers
        },

        ...options
      }
    );

    const text = await response.text();

    if (!response.ok) {

      console.error(
        "Erro da API:",
        response.status,
        text
      );

      throw new Error(
        `Erro ${response.status}: ${text}`
      );
    }

    return text ? JSON.parse(text) : null;

  } catch (error) {

    console.error(
      "Falha na conexão com API:",
      error
    );

    throw error;
  }
}


// MÉTODOS HTTP


export const api = {

  // GET
  get: (endpoint) =>
    request(endpoint),

  // POST
  post: (endpoint, data) =>
    request(endpoint, {
      method: "POST",

      body: JSON.stringify(data)
    }),

  // PUT
  put: (endpoint, data) =>
    request(endpoint, {
      method: "PUT",

      body: JSON.stringify(data)
    }),

  // DELETE
  delete: (endpoint) =>
    request(endpoint, {
      method: "DELETE"
    })
};

