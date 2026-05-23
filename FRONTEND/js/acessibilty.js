document.addEventListener("DOMContentLoaded", () => {
  const themeToggle = document.getElementById("theme-toggle");
  const fontToggle = document.getElementById("font-toggle");

  if (!themeToggle || !fontToggle) {
    console.error("Botões de acessibilidade não encontrados.");
    return;
  }

  const savedTheme = localStorage.getItem("theme");
  const savedFont = localStorage.getItem("large-font");

  if (savedTheme === "dark") {
    document.body.classList.add("dark-mode");
    themeToggle.textContent = "☀️";
  }

  if (savedFont === "true") {
    document.body.classList.add("large-font");
    fontToggle.textContent = "A-";
  }

  themeToggle.addEventListener("click", () => {
    document.body.classList.toggle("dark-mode");

    const isDark = document.body.classList.contains("dark-mode");

    localStorage.setItem("theme", isDark ? "dark" : "light");

    themeToggle.textContent = isDark ? "☀️" : "🌙";
  });

  fontToggle.addEventListener("click", () => {
    document.body.classList.toggle("large-font");

    const isLarge = document.body.classList.contains("large-font");

    localStorage.setItem("large-font", isLarge);

    fontToggle.textContent = isLarge ? "A-" : "A+";
  });
});