const openHamburgerMenu = () => {
  document.getElementById("main").style.marginRight = "250px";
  document.getElementById("hamburger_menu").style.width = "250px";
};
const closeHamburgerMenu = () => {
  document.getElementById("main").style.marginRight = "0";
  document.getElementById("hamburger_menu").style.width = "0";
};

const signInModalPopUp = document.getElementById("modal");

const openModalButton = document.getElementById("open_modal_btn");

const closeModalButton = document.getElementById("close_modal_btn");

const navbar = document.getElementById("navbar");

window.onscroll = function () {
  if (
    document.body.scrollTop >= 200 ||
    document.documentElement.scrollTop >= 200
  ) {
    navbar.style.cssText = `
      background-color: var(--dark); 
      transition: 300ms; 
      height: 13vh; 
      color: white;
      box-shadow: rgba(0, 0, 0, 0.45) 0px 25px 20px -20px;
      `;
  } else {
    navbar.style.cssText = `background-color: var(--light); 
    transition: 280ms; 
    height: 15vh; 
    color: black;`;
  }
};

//active navbar showing current page
let navbarBtns = navbar.getElementsByClassName("nav-link");
for (let i = 0; i < navbarBtns.length; i++) {
  navbarBtns[i].addEventListener('click', function () {
    let current = document.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
    this.className += " active";
  });
}

