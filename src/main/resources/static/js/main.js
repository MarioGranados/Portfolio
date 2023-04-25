const openBtn = document.getElementById("openBtn");
const closeBtn = document.getElementById("closeBtn");
const mobileNavbar = document.getElementById("mobileNavbar");
const header = document.getElementById('header');

function openNavbar() {
  mobileNavbar.style.transform = "translateX(0)";
  header.classList.add('header-opposite')
  openBtn.style.display = "none";
  closeBtn.style.display = "unset";
}
function closeNavbar() {
  mobileNavbar.style.transform = "translateX(100%)";
  header.classList.remove('header-opposite')
  closeBtn.style.display = "none";
  openBtn.style.display = "unset";
}