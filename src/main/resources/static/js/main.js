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

  //active navbar showing current page
  let navbarBtns = navbar.getElementsByClassName("nav_link");
  for (let i = 0; i < navbarBtns.length; i++) {
    navbarBtns[i].addEventListener("click", function () {
      let current = document.getElementsByClassName("active");
      current[0].className = current[0].className.replace(" active", "");
      this.className += " active";
    });
  }

  openModalButton.onclick = function () {
    signInModalPopUp.style.display = "block";
    closeHamburgerMenu();
  };
  closeModalButton.onclick = function () {
    signInModalPopUp.style.display = "none";
  };
  //tap anywhere to close modal window
  window.onclick = function (event) {
    if (event.target == modal) {
      signInModalPopUp.style.display = "none";
    }
  };