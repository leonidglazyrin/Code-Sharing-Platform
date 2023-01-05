textarea = document.querySelector("#code_snippet");
textarea.addEventListener("input", autoResize, false);

function autoResize() {
  this.style.height = "auto";
  this.style.height = this.scrollHeight + 10 + "px";
}

function clearTextArea() {
  textarea.value = "";
}

function send() {
  // var x = document.getElementById("success_message");
  // x.classList.remove("d-none");
  let object = {
    code: document.getElementById("code_snippet").value,
    time:
      document.getElementById("time_restriction").value.trim() == ""
        ? 0
        : parseInt(document.getElementById("time_restriction").value),
    views:
      document.getElementById("views_restriction").value.trim() == ""
        ? 0
        : parseInt(document.getElementById("views_restriction").value),
  };

  console.log(object);

  fetch("/api/code/new", {
    method: "post", // Default is 'get'
    body: JSON.stringify(object),
    mode: "cors",
    headers: new Headers({
      "Content-Type": "application/json; charset=utf-8",
    }),
  })
    .then((response) => response.json())
    .then((json) => {
      console.log(json);
      // alert(json.id);
      var banner = document.getElementById("success_message");
      banner.classList.remove("d-none");
      var uuid = document.getElementById("uuid");
      uuid.innerHTML = " " + json.id;
    });
}
